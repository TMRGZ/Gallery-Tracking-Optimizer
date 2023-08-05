package integration.com.rviewer.skeletons.infrastructure.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.rviewer.skeletons.RviewerSkeletonApplication
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.EventMongoRepository
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.ImageMongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.time.Instant

@SpringBootTest(classes = RviewerSkeletonApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
class AbstractControllerIntegrationSpec extends Specification {

    @Autowired
    WebTestClient webTestClient

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    ImageMongoRepository imageMongoRepository

    @Autowired
    EventMongoRepository eventMongoRepository


    void setup() {
        insertImages()
    }

    private void insertImages() {
        def imagesToInsert = []

        imagesToInsert << ImageDao.builder().id(UUID.randomUUID()).name("IMAGE_1").createdAt(Instant.now()).url("").build()

        imageMongoRepository.saveAll(imagesToInsert).collectList().block()
    }

    void cleanup() {
        def deleteImages = imageMongoRepository.deleteAll()
        def deleteEvents = eventMongoRepository.deleteAll()
        Mono.zip(deleteEvents, deleteImages).block()
    }
}
