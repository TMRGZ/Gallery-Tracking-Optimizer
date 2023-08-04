package unit.com.rviewer.skeletons.infrastructure.config

import com.rviewer.skeletons.infrastructure.config.DatasetClientConfig
import spock.lang.Specification
import spock.lang.Subject

class DatasetClientConfigUnitSpec extends Specification {

    @Subject
    private DatasetClientConfig datasetClientConfig

    void setup() {
        datasetClientConfig = new DatasetClientConfig()
    }

    def "Calling the constructor returns an instance of the object"() {
        when: "The constructor is called"
        def datasetControllerApi = datasetClientConfig.datasetControllerApi()

        then: "The object is indeed, constructed"
        datasetControllerApi
    }
}
