package com.rviewer.skeletons.infrastructure.controller;

import com.rviewer.skeletons.application.service.DebugApplicationService;
import generated.com.rviewer.skeletons.infrastructure.controller.DebugApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class DebugApiController implements DebugApi {

    @Autowired
    private DebugApplicationService debugApplicationService;

    @Override
    public Mono<ResponseEntity<Void>> deleteData(ServerWebExchange exchange) {
        return debugApplicationService.deleteData();
    }

    @Override
    public Mono<ResponseEntity<Void>> importData(ServerWebExchange exchange) {
        return debugApplicationService.importData();
    }
}
