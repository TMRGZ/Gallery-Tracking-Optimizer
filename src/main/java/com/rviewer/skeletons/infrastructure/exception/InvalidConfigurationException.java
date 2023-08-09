package com.rviewer.skeletons.infrastructure.exception;

import com.rviewer.skeletons.domain.exception.GalleryException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidConfigurationException extends GalleryException {

    public InvalidConfigurationException(String message) {
        super(message);
        log.error(message);
    }
}
