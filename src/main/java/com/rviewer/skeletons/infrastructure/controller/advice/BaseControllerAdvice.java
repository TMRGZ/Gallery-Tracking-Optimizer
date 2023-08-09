package com.rviewer.skeletons.infrastructure.controller.advice;

import com.rviewer.skeletons.domain.exception.GalleryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseControllerAdvice {

    @ExceptionHandler(GalleryException.class)
    public ResponseEntity<Void> unexpectedException() {
        return ResponseEntity.internalServerError().build();
    }
}
