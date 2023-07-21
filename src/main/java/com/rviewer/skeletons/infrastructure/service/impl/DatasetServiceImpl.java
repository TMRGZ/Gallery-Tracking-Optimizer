package com.rviewer.skeletons.infrastructure.service.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import com.rviewer.skeletons.infrastructure.mapper.DatasetImageMapper;
import com.rviewer.skeletons.infrastructure.rest.dataset.DatasetControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DatasetServiceImpl implements DatasetService {

    @Autowired
    private DatasetControllerApi datasetControllerApi;

    @Autowired
    private DatasetImageMapper datasetImageMapper;

    @Override
    public Flux<Image> retrieveData() {
        return datasetControllerApi.getData().map(datasetImageMapper::map);
    }
}
