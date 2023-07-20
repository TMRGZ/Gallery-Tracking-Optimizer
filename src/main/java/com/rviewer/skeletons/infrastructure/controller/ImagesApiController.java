package com.rviewer.skeletons.infrastructure.controller;

import com.rviewer.skeletons.application.model.ImageInfoDto;
import com.rviewer.skeletons.application.model.TrackEventBodyDto;
import com.rviewer.skeletons.application.service.ImageApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ImagesApiController implements ImagesApi {

    @Autowired
    private ImageApplicationService imageApplicationService;

    @Override
    public Mono<ResponseEntity<Flux<ImageInfoDto>>> getImageList(ServerWebExchange exchange) {
        return imageApplicationService.getImageList();
    }

    @Override
    public Mono<ResponseEntity<Void>> postImageEvents(String imageId, Mono<TrackEventBodyDto> trackEventBodyDto, ServerWebExchange exchange) {
        return imageApplicationService.postImageEvents(imageId, trackEventBodyDto);
    }
}
