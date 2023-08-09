package unit.com.rviewer.skeletons.domain.algorithm

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum
import spock.lang.Specification
import spock.lang.Subject

import java.time.OffsetDateTime

class ImageSorterAlgorithmUnitSpec extends Specification {

    @Subject
    private ImageSorterAlgorithm imageSorterAlgorithm

    void setup() {
        imageSorterAlgorithm = new ImageSorterAlgorithm(
                "",
                SortDirectionEnum.ASC
        ) {}
    }

    def "An image sorter algorithm by default considers all images equals"() {
        given: "A pair of images"
        def image1 = Image.builder().build()
        def image2 = Image.builder().build()

        when: "The images are compared"
        def compare = imageSorterAlgorithm.compare(image1, image2)

        then: "The result should be equal"
        compare == 0
    }

    def "The fallback compare for images is be default per creation date"() {
        given: "A pair of dates"
        def creationDate1 = OffsetDateTime.MIN
        def creationDate2 = OffsetDateTime.MAX
        and: "A pair of images with creation dates"
        def image1 = Image.builder().createdAt(creationDate1).build()
        def image2 = Image.builder().createdAt(creationDate2).build()

        when: "The images are fallback compared"
        def compare = imageSorterAlgorithm.fallbackCompare().compare(image1, image2)

        then: "The compare is between dates"
        compare == (creationDate2 <=> creationDate1)
    }
}
