package com.rviewer.skeletons.infrastructure.controller.advice;

import com.rviewer.skeletons.domain.exception.ImageNotFoundException;
import com.rviewer.skeletons.domain.exception.NoImagesFoundException;
import com.rviewer.skeletons.infrastructure.controller.ImagesApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ImagesApiController.class)
public class ImagesApiControllerAdvice extends BaseControllerAdvice {

    @ExceptionHandler(NoImagesFoundException.class)
    public ResponseEntity<Void> noImagesFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Void> imageNotFound() {
        return ResponseEntity.notFound().build();
    }
}
