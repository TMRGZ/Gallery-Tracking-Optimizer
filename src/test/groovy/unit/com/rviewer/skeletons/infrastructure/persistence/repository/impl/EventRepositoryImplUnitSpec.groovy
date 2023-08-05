package unit.com.rviewer.skeletons.infrastructure.persistence.repository.impl

import com.rviewer.skeletons.domain.model.Event
import com.rviewer.skeletons.domain.model.enums.EventTypeEnum
import com.rviewer.skeletons.infrastructure.mapper.EventDaoMapper
import com.rviewer.skeletons.infrastructure.persistence.dao.EventDao
import com.rviewer.skeletons.infrastructure.persistence.repository.impl.EventRepositoryImpl
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.EventMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class EventRepositoryImplUnitSpec extends Specification {

    @Subject
    private EventRepositoryImpl eventRepository

    private EventMongoRepository eventMongoRepository = Mock()

    private EventDaoMapper eventDaoMapper = Mock()

    void setup() {
        eventRepository = new EventRepositoryImpl(
                eventMongoRepository: eventMongoRepository,
                eventDaoMapper: eventDaoMapper
        )
    }

    def "Providing an event without id, should store it after map and generate an id and then map again"() {
        given: "An event to store"
        def event = Event.builder().build()
        and: "A mocked mapped dao"
        def eventDao = new EventDao()

        when: "The save is executed"
        def savedEvent = eventRepository.save(event)
        and: "An stored event is returned"
        StepVerifier.create(savedEvent)
                .expectNextMatches { it.id != null }
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * eventDaoMapper.map(eventDao) >> event
        1 * eventDaoMapper.map(event) >> eventDao
        1 * eventMongoRepository.save(eventDao) >> Mono.just(eventDao)
    }

    def "Providing an event with id, should store it after map and then map again"() {
        given: "An event with id to store"
        def eventId = UUID.randomUUID()
        def event = Event.builder().id(eventId).build()
        and: "A mocked mapped dao"
        def eventDao = new EventDao()

        when: "The save is executed"
        def savedEvent = eventRepository.save(event)
        and: "An stored event is returned"
        StepVerifier.create(savedEvent)
                .expectNextMatches { it.id != null }
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * eventDaoMapper.map(eventDao) >> event
        1 * eventDaoMapper.map(event) >> eventDao
        1 * eventMongoRepository.save(eventDao) >> Mono.just(eventDao)
    }

    def "Providing an image id, should find it's events in DB and the map them"() {
        given: "An image id"
        def imageId = UUID.randomUUID()
        and: "A found mocked event dao list"
        def eventDao = new EventDao()
        def eventDaoList = [eventDao]
        and: "A mocked mapped event"
        def event = Event.builder().build()

        when: "The find is executed"
        def foundEvent = eventRepository.findByImageId(imageId)
        and: "A found event is returned"
        StepVerifier.create(foundEvent)
                .expectNext(event)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * eventMongoRepository.findByImageId(imageId) >> Flux.fromIterable(eventDaoList)
        1 * eventDaoMapper.map(eventDao) >> event
    }

    def "Providing an image id and an event type, should count the number of events"() {
        given: "An image id"
        def imageId = UUID.randomUUID()
        and: "An event type"
        def eventType = EventTypeEnum.VIEW
        and: "A number that represents a count"
        def count = 1

        when: "The count is executed"
        def countedEvents = eventRepository.countByImageIdAndEventType(imageId, eventType)
        and: "A found event is returned"
        StepVerifier.create(countedEvents)
                .expectNext(count)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * eventMongoRepository.countByImageIdAndEventType(imageId, eventType) >> Mono.just(count)
    }
}
