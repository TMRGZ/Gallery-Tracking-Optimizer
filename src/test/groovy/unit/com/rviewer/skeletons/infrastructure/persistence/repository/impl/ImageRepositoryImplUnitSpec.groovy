package unit.com.rviewer.skeletons.infrastructure.persistence.repository.impl

import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.infrastructure.mapper.ImageDaoMapper
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao
import com.rviewer.skeletons.infrastructure.persistence.repository.impl.ImageRepositoryImpl
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.ImageMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class ImageRepositoryImplUnitSpec extends Specification {

    @Subject
    private ImageRepositoryImpl imageRepository

    private ImageMongoRepository imageMongoRepository = Mock()

    private ImageDaoMapper imageDaoMapper = Mock()

    void setup() {
        imageRepository = new ImageRepositoryImpl(
                imageMongoRepository: imageMongoRepository,
                imageDaoMapper: imageDaoMapper
        )
    }

    def "Providing nothing, should find all images in DB and the map them"() {
        given: "A returned list of reactive images"
        def imageDao = ImageDao.builder().build()
        def imageDaoList = [imageDao]
        and: "A returned mapped domain model"
        def image = Image.builder().build()

        when: "The find is executed"
        def allImages = imageRepository.findAll()
        and: "The reactive stream is executed"
        StepVerifier.create(allImages)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageMongoRepository.findAll() >> Flux.fromIterable(imageDaoList)
        1 * imageDaoMapper.map({ ImageDao imageDao1 -> imageDao1.events }) >> image
    }

    def "Providing a reactive list of images, should store them after map them and the return their mapped domain version"() {
        given: "A reactive list of mocked images"
        def image = Image.builder().build()
        def imageList = [image]
        def imageFlux = Flux.fromIterable(imageList)
        and: "A mocked mapped dao"
        def imageDao = ImageDao.builder().build()

        when: "The saveAll is executed"
        def storedImages = imageRepository.saveAll(imageFlux)
        and: "The reactive stream is executed"
        StepVerifier.create(storedImages)
                .expectNextSequence(imageList)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageDaoMapper.map(image) >> imageDao
        1 * imageMongoRepository.save(imageDao) >> Mono.just(imageDao)
        1 * imageDaoMapper.map(imageDao) >> image
    }

    def "Providing nothing, should delete all images in DB"() {
        when: "The delete all is executed"
        def deleteAll = imageRepository.deleteAll()
        and: "The reactive stream is executed"
        StepVerifier.create(deleteAll)
                .expectComplete()

        then: "There are interactions with the dependencies"
        1 * imageMongoRepository.deleteAll() >> Mono.empty()
    }

    def "Providing an image id, should find it in DB and them map it"() {
        given: "An image id"
        def imageId = UUID.randomUUID()
        and: "An image dao from DB"
        def imageDao = ImageDao.builder().build()
        imageDao.setId(imageId)
        and: "A mapped domain model image"
        def image = Image.builder().id(imageId).build()

        when: "The find is executed"
        def foundImage = imageRepository.findById(imageId)
        and: "The reactive stream is executed"
        StepVerifier.create(foundImage)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageMongoRepository.findById(imageId) >> Mono.just(imageDao)
        1 * imageDaoMapper.map({ ImageDao imageDao1 -> imageDao1.events }) >> image
    }

    def "Providing an image, should store it in DB after mapping it, and then map the repository response"() {
        given: "An image to store"
        def image = Image.builder().build()
        and: "A image mapped to dao"
        def imageDao = ImageDao.builder().build()

        when: "The save is executed"
        def savedImage = imageRepository.save(image)
        and: "The reactive stream is executed"
        StepVerifier.create(savedImage)
                .expectNext(image)
                .verifyComplete()

        then: "There are interactions with the dependencies"
        1 * imageDaoMapper.map(image) >> imageDao
        1 * imageMongoRepository.save(imageDao) >> Mono.just(imageDao)
        1 * imageDaoMapper.map(imageDao) >> image
    }

}
