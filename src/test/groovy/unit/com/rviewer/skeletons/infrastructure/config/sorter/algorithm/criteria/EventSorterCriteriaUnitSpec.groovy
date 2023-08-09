package unit.com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria

import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.EventSorterCriteria
import spock.lang.Specification

class EventSorterCriteriaUnitSpec extends Specification {

    def "When created the config object, should be able to return it's parameters"() {
        given: "A view parameter"
        def view = 0
        and: "A click parameter"
        def click = 0

        when: "The object is constructed"
        def eventSorterCriteria = new EventSorterCriteria()
        and: "The parameters are passed"
        eventSorterCriteria.setView(view)
        eventSorterCriteria.setClick(click)

        then: "The object is constructed"
        eventSorterCriteria
        and: "The parameters are set"
        eventSorterCriteria.view == view
        eventSorterCriteria.click == click
    }
}
