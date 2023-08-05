package unit.com.rviewer.skeletons.domain.service.debug.impl

import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.service.debug.DebugService
import com.rviewer.skeletons.domain.service.debug.impl.DebugServiceImpl
import com.rviewer.skeletons.domain.service.image.ImageService
import com.rviewer.skeletons.infrastructure.service.DatasetService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class DebugServiceImplUnitSpec extends Specification {

    @Subject
    private DebugService debugService

    private ImageService imageService = Mock()

    private DatasetService datasetService = Mock()

    void setup() {
        debugService = new DebugServiceImpl(
                imageService,
                datasetService
        )
    }

    def "The import data should save the images found in the dataset"() {
        given: "Some data from the dataset"
        def retrievedData = Image.builder().build()
        def retrievedDataList = [retrievedData]
        def retrievedDataFlux = Flux.fromIterable(retrievedDataList)

        when: "The import is executed"
        def importedData = debugService.importData()
        and: "The reactive stream is executed"
        StepVerifier.create(importedData)
                .expectNextSequence(retrievedDataList)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * datasetService.retrieveData() >> retrievedDataFlux
        1 * imageService.saveImages(retrievedDataFlux) >> retrievedDataFlux
    }

    def "The remove data should removed the images saved from the dataset"() {
        when: "The remove is executed"
        def removedData = debugService.removeImportedData()
        and: "The reactive stream is executed"
        StepVerifier.create(removedData)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageService.deleteAllImages() >> Mono.empty()
    }
}
