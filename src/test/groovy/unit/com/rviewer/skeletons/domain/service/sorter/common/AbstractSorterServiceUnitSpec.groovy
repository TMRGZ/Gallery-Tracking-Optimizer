package unit.com.rviewer.skeletons.domain.service.sorter.common

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.repository.ImageRepository
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class AbstractSorterServiceUnitSpec extends Specification {

    @Subject
    private AbstractSorterService abstractSorterService

    private final ImageRepository imageRepository = Mock()

    private final ImageSorterAlgorithm imageSorterAlgorithm = Mock()

    void setup() {
        abstractSorterService = new AbstractSorterService(
                imageRepository,
                imageSorterAlgorithm
        ) {}
    }

    def "The sorter operation will find all images, sort it by the algorithm and index them"() {
        given: "A reactive list of unsorted images"
        def image = Image.builder().build()
        def imageList = [image]
        and: "A mocked sorter algorithm"
        def baseComparator = Mock(Comparator)
        and: "A fallback sorter algorithm"
        def fallbackComparator = Mock(Comparator)

        when: "The sort is executed"
        def sortedImages = abstractSorterService.getSortedImages()
        and: "The reactive stream is executed"
        def reactiveResult = StepVerifier.create(sortedImages)

        then: "The reactive stream contains the expected data"
        reactiveResult
                .expectNextMatches { it.id == image.id && it.gridPosition != null }
                .verifyComplete()
        and: "There are interactions with the dependencies"
        1 * imageRepository.findAll() >> Flux.fromIterable(imageList)
        1 * imageSorterAlgorithm.fallbackCompare() >> fallbackComparator
        1 * imageSorterAlgorithm.thenComparing(fallbackComparator) >> baseComparator
    }

    def "By default preparing the images means just return them"() {
        given: "A reactive list of images"
        def image = Image.builder().build()
        def imageList = [image]
        def imageFlux = Flux.fromIterable(imageList)

        when: "The prepare is executed"
        def preparedImages = abstractSorterService.prepareImages(imageFlux)
        and: "The reactive stream is executed"
        def reactiveResult = StepVerifier.create(preparedImages)

        then: "The reactive stream results in data"
        reactiveResult
                .expectNextSequence(imageList)
                .verifyComplete()
    }

    def "By default the sorter service name would be the algorithm name"() {
        given: "An algorithm name"
        def algorithmName = "NAME"

        when: "The service name is requested"
        def requestedName = abstractSorterService.getAlgorithmName()

        then: "There are interactions"
        1 * imageSorterAlgorithm.getAlgorithmName() >> algorithmName
        and: "The name is the same as the algorithm"
        algorithmName == requestedName
    }
}
