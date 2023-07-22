package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.debug.DebugService;
import com.rviewer.skeletons.domain.service.image.ImageService;
import com.rviewer.skeletons.domain.service.debug.impl.DebugServiceImpl;
import com.rviewer.skeletons.domain.service.image.impl.ImageServiceImpl;
import com.rviewer.skeletons.domain.sorter.factory.SorterFactory;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ImageService imageService(ImageRepository imageRepository, SorterFactory sorterFactory) {
        return new ImageServiceImpl(imageRepository, sorterFactory);
    }

    @Bean
    public DebugService debugService(ImageRepository imageRepository, DatasetService datasetService) {
        return new DebugServiceImpl(imageRepository, datasetService);
    }
}
