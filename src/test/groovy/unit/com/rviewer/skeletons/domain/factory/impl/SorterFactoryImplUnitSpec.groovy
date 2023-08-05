package unit.com.rviewer.skeletons.domain.factory.impl

import com.rviewer.skeletons.domain.factory.SorterFactory
import com.rviewer.skeletons.domain.factory.impl.SorterFactoryImpl
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService
import spock.lang.Specification
import spock.lang.Subject

class SorterFactoryImplUnitSpec extends Specification {

    @Subject
    private SorterFactory sorterFactory

    private List<AbstractSorterService> imageSorterList = []

    private String defaultSorter = "DEFAULT"

    void setup() {
        sorterFactory = new SorterFactoryImpl(
                imageSorterList,
                defaultSorter
        )
    }

    void cleanup() {
        imageSorterList = []
    }

    def "When an existing algorithm is requested then it would be returned"() {
        given: "An algorithm name"
        def algorithmName = "EXISTING"
        and: "A list of configured algorithms"
        def existingAlgorithm = Mock(AbstractSorterService)
        imageSorterList << existingAlgorithm

        when: "The algorithm is requested"
        def foundAlgorithm = sorterFactory.getSorter(algorithmName)

        then: "The algorithm is found"
        foundAlgorithm == existingAlgorithm
        and: "There are mocks interactions"
        1 * existingAlgorithm.algorithmName >> algorithmName
    }

    def "When a non existing algorithm is requested then the default would be returned"() {
        given: "An algorithm name"
        def algorithmName = "NON_EXISTING"
        and: "A list of configured algorithms"
        def defaultAlgorithm = Mock(AbstractSorterService)
        imageSorterList << defaultAlgorithm

        when: "The algorithm is requested"
        def foundAlgorithm = sorterFactory.getSorter(algorithmName)

        then: "The algorithm is found"
        foundAlgorithm == defaultAlgorithm
        and: "There are mocks interactions"
        (1..2) * defaultAlgorithm.algorithmName >> defaultSorter
    }

}
