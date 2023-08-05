package unit.com.rviewer.skeletons.domain.service.event.impl

import com.rviewer.skeletons.domain.exception.ImageNotFoundException
import com.rviewer.skeletons.domain.model.Event
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.model.ImageInfoEvents
import com.rviewer.skeletons.domain.model.enums.EventTypeEnum
import com.rviewer.skeletons.domain.repository.EventRepository
import com.rviewer.skeletons.domain.service.event.EventService
import com.rviewer.skeletons.domain.service.event.impl.EventServiceImpl
import com.rviewer.skeletons.domain.service.image.ImageService
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class EventServiceImplUnitSpec extends Specification {

    @Subject
    private EventService eventService

    private EventRepository eventRepository = Mock()

    private ImageService imageService = Mock()

    void setup() {
        eventService = new EventServiceImpl(
                eventRepository,
                imageService
        )
    }

    def "Providing an event, should be able to relate it to an image and persists the count of that kind of event"() {
        given: "An image from DB"
        def imageId = UUID.randomUUID()
        def imageEvents = ImageInfoEvents.builder().views(BigDecimal.ZERO).clicks(BigDecimal.ZERO).build()
        def image = Image.builder().id(imageId).events(imageEvents).build()
        and: "An event to store"
        def eventType = EventTypeEnum.VIEW
        def event = Event.builder().eventType(eventType).imageId(imageId).build()
        and: "A count from earlier events"
        def eventCount = 1L

        when: "The save is executed"
        def savedEvent = eventService.save(event)
        and: "The reactive stream is executed"
        StepVerifier.create(savedEvent)
                .expectNext(event)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageService.getImage(imageId) >> Mono.just(image)
        1 * eventRepository.save(event) >> Mono.just(event)
        1 * eventRepository.countByImageIdAndEventType(imageId, eventType) >> Mono.just(eventCount)
        1 * imageService.save({Image savedImage -> savedImage.events.views == eventCount}) >> Mono.just(_)
    }

    def "Providing an event with an non existing id, should return an error"() {
        given: "A non existing image id"
        def imageId = UUID.randomUUID()
        and: "An event to store"
        def eventType = EventTypeEnum.VIEW
        def event = Event.builder().eventType(eventType).imageId(imageId).build()
        and: "A count from earlier events"
        def eventCount = 1L

        when: "The save is executed"
        def savedEvent = eventService.save(event)
        and: "The reactive stream is executed"
        StepVerifier.create(savedEvent)
                .expectError(ImageNotFoundException)
                .verify()

        then: "There are interactions with the dependencies"
        1 * imageService.getImage(imageId) >> Mono.empty()
        1 * eventRepository.save(event) >> Mono.just(event)
        1 * eventRepository.countByImageIdAndEventType(imageId, eventType) >> Mono.just(eventCount)
        0 * imageService.save(_) >> Mono.just(_)
    }


    def "Providing and image id it should retrieve all related events"() {
        given: "An image id"
        def imageId = UUID.randomUUID()
        and: "A related event"
        def event = Event.builder().imageId(imageId).build()

        when: "The get is executed"
        def gotEvent = eventService.getAllEventsFromAnImage(imageId)
        and: "The reactive stream is executed"
        StepVerifier.create(gotEvent)
                .expectNext(event)
                .verifyComplete()

        then: "There should be interactions with the dependencies"
        1 * eventRepository.findByImageId(imageId) >> Mono.just(event)
    }

}
