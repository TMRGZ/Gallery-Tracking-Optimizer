package unit.com.rviewer.skeletons.domain.algorithm.impl

import com.rviewer.skeletons.domain.algorithm.impl.EventImageSorterAlgorithm
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum
import spock.lang.Specification
import spock.lang.Subject

class EventImageSorterAlgorithmUnitSpec extends Specification {

    @Subject
    private EventImageSorterAlgorithm eventImageSorterAlgorithm

    void setup() {
        eventImageSorterAlgorithm = new EventImageSorterAlgorithm(
                "",
                SortDirectionEnum.DESC
        )
    }

    def "Sorting images by events means that is sorted by weights"() {
        given: "Two weights"
        def weight1 = BigDecimal.valueOf(Long.MAX_VALUE)
        def weight2 = BigDecimal.valueOf(Long.MIN_VALUE)
        and: "Two images with those weights"
        def image1 = Image.builder().weight(weight1).build()
        def image2 = Image.builder().weight(weight2).build()

        when: "The images are compared"
        def compare = eventImageSorterAlgorithm.compare(image1, image2)

        then: "Are compared by weight taking the direction"
        compare ==
                (eventImageSorterAlgorithm.direction == SortDirectionEnum.ASC
                ? (weight1 <=> weight2) : (weight2 <=> weight1))
    }
}
