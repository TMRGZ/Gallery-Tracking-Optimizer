package unit.com.rviewer.skeletons.infrastructure.controller

import com.rviewer.skeletons.application.model.TrackEventBodyDto
import com.rviewer.skeletons.application.service.ImageApplicationService
import com.rviewer.skeletons.infrastructure.controller.ImagesApiController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject

import java.time.OffsetDateTime

class ImagesApiControllerUnitSpec extends Specification {

    @Subject
    private ImagesApiController imagesApiController

    private ImageApplicationService imageApplicationService = Mock(ImageApplicationService)

    void setup() {
        imagesApiController = new ImagesApiController(
                imageApplicationService: imageApplicationService
        )
    }

    def "When receiving a get image list request results in a call to the image application service"() {
        given: "A mocked server web exchange object"
        def exchange = Mock(ServerWebExchange)

        when: "Calling the method"
        imagesApiController.getImageList(exchange)

        then: "There are interaction with the dependencies"
        1 * imageApplicationService.getImageList()
    }

    def "When receiving a post image event request results in a call to the image application service"() {
        given: "A mocked server web exchange object"
        def exchange = Mock(ServerWebExchange)
        and: "An image id"
        def imageId = UUID.randomUUID().toString()
        and: "A reactive track event object"
        def eventType = TrackEventBodyDto.EventTypeEnum.VIEW
        def timestamp = OffsetDateTime.now()
        def trackEventBodyDto = Mono.just(new TrackEventBodyDto(eventType, timestamp))

        when: "Calling the method"
        imagesApiController.postImageEvents(imageId, trackEventBodyDto, exchange)

        then: "There are interaction with the dependencies"
        1 * imageApplicationService.postImageEvents(imageId, trackEventBodyDto)
    }

}
