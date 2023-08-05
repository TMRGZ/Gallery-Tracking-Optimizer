package unit.com.rviewer.skeletons.infrastructure.controller.advice

import com.rviewer.skeletons.infrastructure.controller.advice.BaseControllerAdvice
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class BaseControllerAdviceUnitSpec extends Specification {

    @Subject
    private BaseControllerAdvice baseControllerAdvice

    void setup() {
        baseControllerAdvice = new BaseControllerAdvice() {}
    }

    def "The gallery exception handler should return a 500"() {
        when: "The handler is started"
        def handled = baseControllerAdvice.unexpectedException()

        then: "It's a 500 without body"
        handled.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        !handled.body
    }
}
