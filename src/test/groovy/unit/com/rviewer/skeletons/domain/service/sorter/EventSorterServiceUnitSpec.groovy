package unit.com.rviewer.skeletons.domain.service.sorter

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.model.ImageInfoEvents
import com.rviewer.skeletons.domain.repository.ImageRepository
import com.rviewer.skeletons.domain.service.sorter.EventSorterService
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class EventSorterServiceUnitSpec extends Specification {

    @Subject
    private EventSorterService eventSorterService

    private ImageRepository imageRepository = Mock()

    private ImageSorterAlgorithm imageSorterAlgorithm = Mock()

    private Double viewRating = 0.5

    private Double clickRating = 0.5

    void setup() {
        eventSorterService = new EventSorterService(
                imageRepository,
                imageSorterAlgorithm,
                viewRating,
                clickRating
        )
    }

    def "Preparing the images for an event sorter mean calculate their weights"() {
        given: "A reactive list of images"
        def imageEvents = ImageInfoEvents.builder().clicks(BigDecimal.ZERO).views(BigDecimal.ZERO).build()
        def image = Image.builder().events(imageEvents).build()
        def imageList = [image]
        def imageFlux = Flux.fromIterable(imageList)

        when: "The prepare is executed"
        def preparedImages = eventSorterService.prepareImages(imageFlux)
        and: "The reactive stream is executed"
        def reactiveResult = StepVerifier.create(preparedImages)

        then: "The reactive stream has the processed data"
        reactiveResult.expectNextMatches { it.weight != null }
                .verifyComplete()
    }

}
