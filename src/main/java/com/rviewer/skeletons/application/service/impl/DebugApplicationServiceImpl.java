package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.service.DebugApplicationService;
import com.rviewer.skeletons.domain.service.debug.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class DebugApplicationServiceImpl implements DebugApplicationService {

    @Autowired
    private DebugService debugService;

    @Override
    @Transactional
    public Mono<ResponseEntity<Void>> importData() {
        return debugService.importData()
                .then(Mono.just(ResponseEntity.created(null).build()));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteData() {
        return debugService.removeImportedData()
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
