package unit.com.rviewer.skeletons.infrastructure.config

import com.rviewer.skeletons.domain.factory.SorterFactory
import com.rviewer.skeletons.domain.repository.EventRepository
import com.rviewer.skeletons.domain.repository.ImageRepository
import com.rviewer.skeletons.domain.service.image.ImageService
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService
import com.rviewer.skeletons.infrastructure.config.BeanConfig
import com.rviewer.skeletons.infrastructure.config.sorter.SorterConfig
import com.rviewer.skeletons.infrastructure.service.DatasetService
import spock.lang.Specification
import spock.lang.Subject

class BeanConfigUnitSpec extends Specification {

    @Subject
    private BeanConfig beanConfig

    void setup() {
        beanConfig = new BeanConfig()
    }

    def "A Sorter factory impl should be passed as an interface"() {
        given: "A list of mocked sorter services"
        def imageSorterList = [Mock(AbstractSorterService)]
        and: "A mocked sorter config"
        def sorterConfig = Mock(SorterConfig)

        when: "The constructor is executed"
        def sorterFactory = beanConfig.sorterFactory(imageSorterList, sorterConfig)

        then: "The object is indeed, constructed"
        sorterFactory
    }

    def "An Image service impl should be passed as an interface"() {
        given: "A mocked image repository"
        def imageRepository = Mock(ImageRepository)
        and: "A mocked sorter factory"
        def sorterFactory = Mock(SorterFactory)

        when: "The constructor is executed"
        def imageService = beanConfig.imageService(imageRepository, sorterFactory)

        then: "The object is indeed, constructed"
        imageService
    }

    def "An event service impl should be passed as an interface"() {
        given: "A mocked event repository"
        def eventRepository = Mock(EventRepository)
        and: "A mocked image service"
        def imageService = Mock(ImageService)

        when: "The constructor is executed"
        def eventService = beanConfig.eventService(eventRepository, imageService)

        then: "The object is indeed, constructed"
        eventService
    }

    def "A debug service impl should be passed as an interface"() {
        given: "A mocked image service"
        def imageService = Mock(ImageService)
        and: "A mocked dataset service"
        def datasetService = Mock(DatasetService)

        when: "The constructor is executed"
        def debugService = beanConfig.debugService(imageService, datasetService)

        then: "The object is indeed, constructed"
        debugService
    }
}
