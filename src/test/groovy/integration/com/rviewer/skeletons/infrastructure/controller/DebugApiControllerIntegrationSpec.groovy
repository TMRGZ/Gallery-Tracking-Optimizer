package integration.com.rviewer.skeletons.infrastructure.controller

import com.github.tomakehurst.wiremock.client.WireMock
import generated.com.rviewer.skeletons.infrastructure.rest.dataset.model.DatasetImageDto
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

class DebugApiControllerIntegrationSpec extends AbstractControllerIntegrationSpec {

    private static final String DEBUG_IMAGES_ENDPOINT = "/debug/images"
    private static final String DATASET_GET_DATA_ENDPOINT = "/challenges/datasets/gallery-tracking-optimizer/data.json"

    def "Receiving a delete all request, responds with a 204 after deleting data"() {
        expect: "That the repository is not empty"
        imageMongoRepository.count().block() > 0

        when: "The endpoint is called"
        def response = webTestClient.delete().uri(DEBUG_IMAGES_ENDPOINT)
                .exchange()

        then: "The response is a 204 with no body"
        response.expectStatus().isNoContent()
                .expectBody().isEmpty()
        and: "The DB is empty"
        imageMongoRepository.count().block() == 0
    }

    def "Receiving an import request, responds with a 201 after successfully importing data"() {
        given: "A list of images from the dataset"
        def datasetImage = new DatasetImageDto().id(UUID.randomUUID()).name("TEST")
        def datasetImageList = [datasetImage]
        and: "A mocked dataset endpoint"
        def datasetUri = URI.create(DATASET_GET_DATA_ENDPOINT)
        WireMock.stubFor(WireMock.get(datasetUri.getPath())
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(datasetImageList))
                )
        )

        when: "The endpoint is called"
        def response = webTestClient.post().uri(DEBUG_IMAGES_ENDPOINT)
                .exchange()

        then: "The response is a 201 with no body"
        response.expectStatus().isCreated()
                .expectBody().isEmpty()
        and: "The DB contains the data from the dataset"
        def storedImageIdList = imageMongoRepository.findAll()
                .map { it.id }
                .collectList().block()
        def datasetImageIdList = datasetImageList
                .collect { it.id }
                .toList()
        storedImageIdList.containsAll(datasetImageIdList)
    }
}
