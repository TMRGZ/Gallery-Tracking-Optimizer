package unit.com.rviewer.skeletons.infrastructure.controller.advice

import com.rviewer.skeletons.infrastructure.controller.advice.ImagesApiControllerAdvice
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class ImagesApiControllerAdviceUnitSpec extends Specification {

    @Subject
    private ImagesApiControllerAdvice imagesApiControllerAdvice

    void setup() {
        imagesApiControllerAdvice = new ImagesApiControllerAdvice()
    }

    def "A no images exception handler should return a 404 without body"() {
        when: "The handler is executed"
        def handled = imagesApiControllerAdvice.noImagesFound()

        then: "It's a 404 without body"
        handled.statusCode == HttpStatus.NOT_FOUND
        !handled.body
    }

    def "A image not found exception handler should return a 404 without body"() {
        when: "The handler is executed"
        def handled = imagesApiControllerAdvice.imageNotFound()

        then: "It's a 404 without body"
        handled.statusCode == HttpStatus.NOT_FOUND
        !handled.body
    }
}
