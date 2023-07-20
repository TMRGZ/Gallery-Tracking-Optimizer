package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;

public interface ImageService {

    Flux<Image> getImages();

}
