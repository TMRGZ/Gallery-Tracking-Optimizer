package unit.com.rviewer.skeletons.infrastructure.config.sorter

import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum
import com.rviewer.skeletons.domain.repository.ImageRepository
import com.rviewer.skeletons.infrastructure.config.sorter.SorterList
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.EventSorterConfig
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.EventSorterCriteria
import spock.lang.Specification
import spock.lang.Subject

class SorterListUnitSpec extends Specification {

    @Subject
    private SorterList sorterList

    private EventSorterConfig eventSorterConfig = Mock(EventSorterConfig)

    void setup() {
        sorterList = new SorterList(
                eventSorter: eventSorterConfig
        )
    }

    def "An event sorter service should be constructed as an abstract class"() {
        given: "A mocked image repository"
        def imageRepository = Mock(ImageRepository)
        and: "A mocked criteria"
        def criteria = Mock(EventSorterCriteria)
        and: "Criteria rates"
        def viewCriteria = 0
        def clickCriteria = 0

        when: "The constructor is called"
        def abstractSorterService = sorterList.eventSorterService(imageRepository)

        then: "There are interactions with the dependencies"
        1 * eventSorterConfig.getName() >> "TEST"
        1 * eventSorterConfig.getDirection() >> SortDirectionEnum.ASC
        2 * eventSorterConfig.getCriteria() >> criteria
        1 * criteria.getClick() >> clickCriteria
        1 * criteria.getView() >> viewCriteria
        and: "The object is indeed, constructed"
        abstractSorterService
    }
}
