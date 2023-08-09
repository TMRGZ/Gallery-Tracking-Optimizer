package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.infrastructure.mapper.ImageDaoMapper;
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao;
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageInfoEventsDao;
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.ImageMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Component
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private ImageMongoRepository imageMongoRepository;

    @Autowired
    private ImageDaoMapper imageDaoMapper;

    @Override
    public Flux<Image> findAll() {
        return imageMongoRepository.findAll()
                .map(this::defaultEvents)
                .map(imageDaoMapper::map);
    }

    @Override
    public Mono<Void> deleteAll() {
        return imageMongoRepository.deleteAll();
    }

    @Override
    public Mono<Image> findById(UUID id) {
        return imageMongoRepository.findById(id)
                .map(this::defaultEvents)
                .map(imageDaoMapper::map);
    }

    private ImageDao defaultEvents(ImageDao imageDao) {
        return Objects.isNull(imageDao.getEvents())
                ? imageDao.toBuilder().events(ImageInfoEventsDao.builder().build()).build()
                : imageDao;
    }

    @Override
    public Mono<Image> save(Image image) {
        return imageMongoRepository.save(imageDaoMapper.map(image)).map(imageDaoMapper::map);
    }
}
