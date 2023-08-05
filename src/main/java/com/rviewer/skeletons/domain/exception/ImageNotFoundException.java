package com.rviewer.skeletons.domain.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ImageNotFoundException extends GalleryException {

    public ImageNotFoundException(UUID imageId) {
        super();
        log.error("Image with id = {} not found", imageId);
    }
}
