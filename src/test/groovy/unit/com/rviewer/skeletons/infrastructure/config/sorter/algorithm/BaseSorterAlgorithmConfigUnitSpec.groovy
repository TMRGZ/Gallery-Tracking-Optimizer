package unit.com.rviewer.skeletons.infrastructure.config.sorter.algorithm

import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.BaseSorterAlgorithmConfig
import spock.lang.Specification

abstract class BaseSorterAlgorithmConfigUnitSpec extends Specification {

    BaseSorterAlgorithmConfig sorterAlgorithmConfig

    def "When created the config object, should be able to return it's parameters"() {
        given: "A name parameter"
        def name = "TEST"
        and: "A direction parameter"
        def direction = SortDirectionEnum.ASC

        when: "The parameters are passed to the object"
        sorterAlgorithmConfig.name = name
        sorterAlgorithmConfig.direction = direction

        then: "The parameters are set"
        sorterAlgorithmConfig.name == name
        sorterAlgorithmConfig.direction == direction
    }
}
