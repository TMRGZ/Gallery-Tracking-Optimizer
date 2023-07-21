package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.infrastructure.mapper.ImageDaoMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.ImageMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private ImageMongoRepository imageMongoRepository;

    @Autowired
    private ImageDaoMapper imageDaoMapper;

    @Override
    public Flux<Image> findAll() {
        return imageMongoRepository.findAll().map(imageDaoMapper::map);
    }

    @Override
    public Flux<Image> saveAll(Flux<Image> imageFlux) {
        return imageMongoRepository.saveAll(imageFlux.map(imageDaoMapper::map))
                .map(imageDaoMapper::map);
    }

    @Override
    public Mono<Void> deleteAll() {
        return imageMongoRepository.deleteAll();
    }
}
