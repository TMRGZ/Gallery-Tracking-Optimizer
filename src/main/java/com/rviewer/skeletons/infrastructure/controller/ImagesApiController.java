package com.rviewer.skeletons.infrastructure.controller;

import com.rviewer.skeletons.application.model.ImageInfoDto;
import com.rviewer.skeletons.application.model.TrackEventBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ImagesApiController implements ImagesApi {


    @Override
    public Mono<ResponseEntity<Flux<ImageInfoDto>>> getImageList(ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> postImageEvents(String imageId, Mono<TrackEventBodyDto> trackEventBodyDto, ServerWebExchange exchange) {
        return null;
    }
}
