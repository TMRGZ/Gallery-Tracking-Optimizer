package unit.com.rviewer.skeletons.infrastructure.config.sorter

import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService
import com.rviewer.skeletons.infrastructure.config.sorter.SorterConfig
import com.rviewer.skeletons.infrastructure.exception.InvalidConfigurationException
import spock.lang.Specification
import spock.lang.Subject

class SorterConfigUnitSpec extends Specification {

    @Subject
    private SorterConfig sorterConfig

    private String defaultAlgorithm = "TEST"

    void setup() {
        sorterConfig = new SorterConfig(
                defaultAlgorithm: defaultAlgorithm
        )
    }

    def "A default sorter should be returned when the default sorter is provided"() {
        given: "A list of mocked sorter services"
        def sorterService = Mock(AbstractSorterService)
        def imageSorterList = [sorterService]

        when: "Calling the constructor"
        def defaultSorter = sorterConfig.defaultSorter(imageSorterList)

        then: "The mocked algorithm's name is found"
        1 * sorterService.getAlgorithmName() >> defaultAlgorithm
        and: "The object is indeed, constructed"
        defaultSorter
    }

    def "An invalid configuration exception should be thrown when the default sorter is not provided"() {
        given: "A list of mocked sorter services"
        def sorterService = Mock(AbstractSorterService)
        def imageSorterList = [sorterService]

        when: "Calling the constructor"
        def defaultSorter = sorterConfig.defaultSorter(imageSorterList)

        then: "The mocked algorithm's name is not found"
        1 * sorterService.getAlgorithmName() >> "NOT_FOUND"
        and: "An exception is thrown"
        thrown(InvalidConfigurationException)
        and: "The object is not constructed"
        !defaultSorter
    }
}
