package com.rviewer.skeletons.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoImagesFoundException extends GalleryException {

    public NoImagesFoundException() {
        super();
        log.error("There are no images registered");
    }
}
