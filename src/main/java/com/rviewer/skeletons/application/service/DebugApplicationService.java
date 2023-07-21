package com.rviewer.skeletons.application.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface DebugApplicationService {

    Mono<ResponseEntity<Void>> importData();

    Mono<ResponseEntity<Void>> deleteData();
}
