package unit.com.rviewer.skeletons.application.service.impl

import com.rviewer.skeletons.application.mapper.EventDtoMapper
import com.rviewer.skeletons.application.mapper.ImageDtoMapper
import generated.com.rviewer.skeletons.application.model.ImageInfoDto
import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto
import com.rviewer.skeletons.application.service.ImageApplicationService
import com.rviewer.skeletons.application.service.impl.ImageApplicationServiceImpl
import com.rviewer.skeletons.domain.model.Event
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.service.event.EventService
import com.rviewer.skeletons.domain.service.image.ImageService
import org.springframework.http.HttpStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

import java.time.OffsetDateTime

class ImageApplicationServiceImplUnitSpec extends Specification {

    @Subject
    private ImageApplicationService imageApplicationService

    private ImageService imageService = Mock(ImageService)

    private ImageDtoMapper imageDtoMapper = Mock(ImageDtoMapper)

    private EventService eventService = Mock(EventService)

    private EventDtoMapper eventDtoMapper = Mock(EventDtoMapper)

    void setup() {
        imageApplicationService = new ImageApplicationServiceImpl(
                imageService: imageService,
                imageDtoMapper: imageDtoMapper,
                eventService: eventService,
                eventDtoMapper: eventDtoMapper
        )
    }

    def "Receiving a get request results in an reactive list of images"() {
        given: "An supported algorithm"
        def algorithm = ""
        and: "A mocked service response"
        def image = Image.builder().build()
        def imagesFlux = Flux.just(image)
        and: "A mocked mapped object"
        def imageDto = new ImageInfoDto()

        when: "The parameters are received"
        def response = imageApplicationService.getImageList()
        and: "The reactive stream is executed"
        StepVerifier.create(response).expectNextMatches {
            it.statusCode == HttpStatus.OK && it.body
                    && StepVerifier.create(it.body)
                    .expectNext(imageDto)
                    .verifyComplete()
        }.verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageService.getSortedImages(algorithm) >> imagesFlux
        1 * imageDtoMapper.map(image) >> imageDto
    }

    def "Receiving a post image event request results in an empty response"() {
        given: "An image id"
        def imageId = UUID.randomUUID().toString()
        and: "An event"
        def eventEnum = TrackEventBodyDto.EventTypeEnum.VIEW
        def time = OffsetDateTime.now()
        def trackEventBodyDto = new TrackEventBodyDto(eventEnum, time)
        def trackEventBodyDtoMono = Mono.just(trackEventBodyDto)
        and: "A mocked mapped object"
        def event = Event.builder().build()
        and: "A saved object"
        def savedEvent = event
        def savedEventMono = Mono.just(savedEvent)

        when: "The parameters are received"
        def response = imageApplicationService.postImageEvents(imageId, trackEventBodyDtoMono)
        and: "The reactive stream is executed"
        StepVerifier.create(response).expectNextMatches { it.statusCode == HttpStatus.NO_CONTENT && !it.body }
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * eventDtoMapper.map(trackEventBodyDto) >> event
        1 * eventService.save(_ as Event) >> savedEventMono
    }
}
