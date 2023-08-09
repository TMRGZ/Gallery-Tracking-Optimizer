package unit.com.rviewer.skeletons.infrastructure.controller

import com.rviewer.skeletons.application.service.DebugApplicationService
import com.rviewer.skeletons.infrastructure.controller.DebugApiController
import org.springframework.web.server.ServerWebExchange
import spock.lang.Specification
import spock.lang.Subject

class DebugApiControllerUnitSpec extends Specification {

    @Subject
    private DebugApiController debugApiController

    private DebugApplicationService debugApplicationService = Mock(DebugApplicationService)

    void setup() {
        debugApiController = new DebugApiController(
                debugApplicationService: debugApplicationService
        )
    }

    def "Receiving a delete data request results in a call to the debug application service"() {
        given: "A mocked server web exchange object"
        def exchange = Mock(ServerWebExchange)

        when: "Calling the method"
        debugApiController.deleteData(exchange)

        then: "There are interaction with the dependencies"
        1 * debugApplicationService.deleteData()
    }

    def "Receiving an import data request results in a call to the debug application service"() {
        given: "A mocked server web exchange object"
        def exchange = Mock(ServerWebExchange)

        when: "Calling the method"
        debugApiController.importData(exchange)

        then: "There are interaction with the dependencies"
        1 * debugApplicationService.importData()
    }

}
