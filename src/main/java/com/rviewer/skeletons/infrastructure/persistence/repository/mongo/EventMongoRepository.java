package com.rviewer.skeletons.infrastructure.persistence.repository.mongo;

import com.rviewer.skeletons.infrastructure.persistence.dao.EventDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface EventMongoRepository extends ReactiveMongoRepository<EventDao, UUID> {

    Flux<EventDao> findByImageId(UUID imageId);
}
