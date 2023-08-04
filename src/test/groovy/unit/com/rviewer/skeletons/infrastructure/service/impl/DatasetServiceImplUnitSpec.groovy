package unit.com.rviewer.skeletons.infrastructure.service.impl

import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.infrastructure.mapper.DatasetImageDaoMapper
import com.rviewer.skeletons.infrastructure.rest.dataset.DatasetControllerApi
import com.rviewer.skeletons.infrastructure.rest.dataset.model.DatasetImageDto
import com.rviewer.skeletons.infrastructure.service.DatasetService
import com.rviewer.skeletons.infrastructure.service.impl.DatasetServiceImpl
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class DatasetServiceImplUnitSpec extends Specification {

    @Subject
    private DatasetService datasetService

    private DatasetControllerApi datasetControllerApi = Mock()

    private DatasetImageDaoMapper datasetImageDaoMapper = Mock()

    void setup() {
        datasetService = new DatasetServiceImpl(
                datasetControllerApi: datasetControllerApi,
                datasetImageDaoMapper: datasetImageDaoMapper
        )
    }

    def "When a retrieve data is executed should return the data and map it"() {
        given: "A response from the dataset"
        def dataResponse = new DatasetImageDto()
        def dataResponseList = [dataResponse]
        and: "A list of mapped images"
        def image = Image.builder().build()

        when: "The retrieve is executed"
        def retrievedImages = datasetService.retrieveData()
        and: "The reactive stream is executed"
        StepVerifier.create(retrievedImages)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * datasetControllerApi.getData() >> Flux.fromIterable(dataResponseList)
        1 * datasetImageDaoMapper.map(dataResponse) >> image
    }
}
