package integration.com.rviewer.skeletons.infrastructure.controller


import generated.com.rviewer.skeletons.application.model.ImageInfoDto
import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto

import java.time.OffsetDateTime

class ImagesApiControllerIntegrationSpec extends AbstractControllerIntegrationSpec {

    private static final String IMAGES_ENDPOINT = "/images"
    private static final String IMAGE_EVENTS_ENDPOINT = IMAGES_ENDPOINT + "/{imageId}/events"

    def "Receiving a get images request should return a 200 after responding with a list of sorted images by events"() {
        when: "The endpoint is called"
        def response = webTestClient.get().uri(IMAGES_ENDPOINT)
                .exchange()

        then: "The response is a 200 with the list of sorted images"
        def body = response.expectStatus().isOk()
                .expectBodyList(ImageInfoDto).returnResult().responseBody
        and: "The images are sorted by weight"
        body == body.sort { it.weight }
    }

    def "Receiving a get images request should return a 404 when there are no images"() {
        given: "That there are no images in DB"
        imageMongoRepository.deleteAll().block()

        when: "The endpoint is called"
        def response = webTestClient.get().uri(IMAGES_ENDPOINT)
                .exchange()

        then: "The response is a 404"
        response.expectStatus().isNotFound()
    }

    def "Receiving a post event request, should return a 204 after registering the event to the image"() {
        given: "An existing image"
        def imageId = imagesToInsert.find{it.name == "IMAGE_1"}.id
        and: "A tracking event object"
        def trackingEvent = new TrackEventBodyDto(TrackEventBodyDto.EventTypeEnum.CLICK, OffsetDateTime.now())

        when: "The endpoint is called"
        def response = webTestClient.post().uri(IMAGE_EVENTS_ENDPOINT, imageId.toString())
                .bodyValue(trackingEvent)
                .exchange()

        then: "A 204 should be received with no body"
        response.expectStatus().isNoContent()
                .expectBody().isEmpty()
        and: "There is an event related to the image"
        1 == eventMongoRepository.findByImageId(imageId).count().block()
        and: "There is one click event registered in the image"
        BigDecimal.ONE == imageMongoRepository.findById(imageId).block().events.clicks
    }

    def "Receiving a post event request, should return a 404 when the imageId does not exist"() {
        given: "A non existing image"
        def imageId = UUID.randomUUID()
        and: "A tracking event object"
        def trackingEvent = new TrackEventBodyDto(TrackEventBodyDto.EventTypeEnum.CLICK, OffsetDateTime.now())

        when: "The endpoint is called"
        def response = webTestClient.post().uri(IMAGE_EVENTS_ENDPOINT, imageId.toString())
                .bodyValue(trackingEvent)
                .exchange()

        then: "A 404 should be received with no body"
        response.expectStatus().isNotFound()
                .expectBody().isEmpty()
        and: "There is not an event related to the image"
        0 == eventMongoRepository.findByImageId(imageId).count().block()
    }
}
