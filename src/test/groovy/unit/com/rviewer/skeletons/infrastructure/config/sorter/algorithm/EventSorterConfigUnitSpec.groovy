package unit.com.rviewer.skeletons.infrastructure.config.sorter.algorithm


import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.EventSorterConfig
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.EventSorterCriteria
import spock.lang.Subject

class EventSorterConfigUnitSpec extends BaseSorterAlgorithmConfigUnitSpec {

    @Subject
    private EventSorterConfig eventSorterConfig

    void setup() {
        eventSorterConfig = new EventSorterConfig()
        sorterAlgorithmConfig = eventSorterConfig
    }

    def "When created the config object, should be able to return it's parameters"() {
        given: "A mocked event sorter criteria parameter"
        def eventSorterCriteria = Mock(EventSorterCriteria)

        when: "The parameters are passed"
        eventSorterConfig.setCriteria(eventSorterCriteria)

        then: "The object is constructed"
        eventSorterConfig
        and: "The parameters are set"
        eventSorterConfig.criteria == eventSorterCriteria
    }
}
