package com.rviewer.skeletons.domain.service.debug;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebugService {

    Flux<Image> importData();

    Mono<Void> removeImportedData();

}
