package unit.com.rviewer.skeletons.domain.service.image.impl

import com.rviewer.skeletons.domain.factory.SorterFactory
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.repository.ImageRepository
import com.rviewer.skeletons.domain.service.image.ImageService
import com.rviewer.skeletons.domain.service.image.impl.ImageServiceImpl
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class ImageServiceImplUnitSpec extends Specification {

    @Subject
    private ImageService imageService

    private ImageRepository imageRepository = Mock()

    private SorterFactory sorterFactory = Mock()

    void setup() {
        imageService = new ImageServiceImpl(
                imageRepository,
                sorterFactory
        )
    }

    def "Providing an algorithm name should get the sorter and then sort the images"() {
        given: "An algorithm name"
        def algorithmName = "ALGORITHM"
        and: "A mocked sorter"
        def sorter = Mock(AbstractSorterService)
        and: "A list of images"
        def image = Image.builder().build()
        def imageList = [image]

        when: "Getting the images"
        def sortedImages = imageService.getSortedImages(algorithmName)
        and: "The reactive stream is executed"
        StepVerifier.create(sortedImages)
                .expectNext(image)
                .verifyComplete()

        then: "Should be interactions between dependencies"
        1 * sorterFactory.getSorter(algorithmName) >> sorter
        1 * sorter.getSortedImages() >> Flux.fromIterable(imageList)
    }

    def "Providing an image id should find that image in DB"() {
        given: "An image id"
        def imageId = UUID.randomUUID()
        and: "An image"
        def image = Image.builder().id(imageId).build()

        when: "The get is executed"
        def gotImage = imageService.getImage(imageId)
        and: "The reactive stream is executed"
        StepVerifier.create(gotImage)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageRepository.findById(imageId) >> Mono.just(image)
    }

    def "Providing an image should store it in DB"() {
        given: "An image to store"
        def image = Image.builder().build()

        when: "The image is saved"
        def savedImage = imageService.save(image)
        and: "The reactive stream is executed"
        StepVerifier.create(savedImage)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with dependencies"
        1 * imageRepository.save(image) >> Mono.just(image)
    }

    def "Providing an image list should store them in DB"() {
        given: "A reactive list of images"
        def image = Image.builder().build()
        def imageList = [image]
        def imageFlux = Flux.fromIterable(imageList)

        when: "The save is executed"
        def savedImages = imageService.saveImages(imageFlux)
        and: "The reactive stream is executed"
        StepVerifier.create(savedImages)
                .expectNextSequence(imageList)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageRepository.save(image) >> Mono.just(image)
    }

    def "Providing nothing, all images should be deleted from the DB"() {
        when: "The delete is executed"
        def deleted = imageService.deleteAllImages()
        and: "The reactive stream is executed"
        StepVerifier.create(deleted)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageRepository.deleteAll() >> Mono.empty()
    }
}
