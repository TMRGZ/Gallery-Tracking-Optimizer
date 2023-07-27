package com.rviewer.skeletons.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageNotFoundException extends GalleryException {

    public ImageNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
