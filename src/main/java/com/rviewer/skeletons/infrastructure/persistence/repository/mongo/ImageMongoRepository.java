package com.rviewer.skeletons.infrastructure.persistence.repository.mongo;

import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageMongoRepository extends ReactiveMongoRepository<ImageDao, UUID> {
}
