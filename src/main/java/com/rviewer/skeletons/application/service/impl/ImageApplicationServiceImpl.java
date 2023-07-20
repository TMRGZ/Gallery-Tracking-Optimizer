package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.mapper.ImageDtoMapper;
import com.rviewer.skeletons.application.model.ImageInfoDto;
import com.rviewer.skeletons.application.model.TrackEventBodyDto;
import com.rviewer.skeletons.application.service.ImageApplicationService;
import com.rviewer.skeletons.domain.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImageApplicationServiceImpl implements ImageApplicationService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageDtoMapper imageDtoMapper;

    @Override
    public Mono<ResponseEntity<Flux<ImageInfoDto>>> getImageList() {
        Flux<ImageInfoDto> imageInfoDtoFlux = imageService.getImages().map(imageDtoMapper::map);
        return Mono.just(ResponseEntity.ok(imageInfoDtoFlux));
    }

    @Override
    public Mono<ResponseEntity<Void>> postImageEvents(String imageId, Mono<TrackEventBodyDto> trackEventBodyDto) {
        return null;
    }
}
