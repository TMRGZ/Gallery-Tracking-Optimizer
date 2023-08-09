package unit.com.rviewer.skeletons.application.service.impl

import com.rviewer.skeletons.application.service.DebugApplicationService
import com.rviewer.skeletons.application.service.impl.DebugApplicationServiceImpl
import com.rviewer.skeletons.domain.model.Image
import com.rviewer.skeletons.domain.service.debug.DebugService
import org.springframework.http.HttpStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class DebugApplicationServiceImplUnitSpec extends Specification {

    @Subject
    private DebugApplicationService debugApplicationService

    private DebugService debugService = Mock(DebugService)

    void setup() {
        debugApplicationService = new DebugApplicationServiceImpl(
                debugService: debugService
        )
    }

    def "When an import data request is received it gets an empty response"() {
        given: "A mocked imported data list"
        def image = Image.builder().build()
        def imageFlux = Flux.just(image)

        when: "The request is executed"
        def response = debugApplicationService.importData()
        and: "The reactive stream is executed"
        def reactiveResponse = StepVerifier.create(response)

        then: "A response is received"
        reactiveResponse
                .expectNextMatches { it.statusCode == HttpStatus.CREATED && !it.body }
                .verifyComplete()
        and: "There are interactions with dependencies"
        1 * debugService.importData() >> imageFlux
    }

    def "When a delete data request is received it gets an empty response"() {
        when: "The request is executed"
        def response = debugApplicationService.deleteData()
        and: "The reactive stream is executed"
        def reactiveResponse = StepVerifier.create(response)

        then: "A response is received"
        reactiveResponse
                .expectNextMatches { it.statusCode == HttpStatus.NO_CONTENT && !it.body }
                .verifyComplete()
        and: "There are interactions with dependencies"
        1 * debugService.removeImportedData() >> Mono.empty()
    }
}
