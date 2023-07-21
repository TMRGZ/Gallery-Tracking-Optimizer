package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.DebugService;
import com.rviewer.skeletons.domain.service.ImageService;
import com.rviewer.skeletons.domain.service.impl.DebugServiceImpl;
import com.rviewer.skeletons.domain.service.impl.ImageServiceImpl;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ImageService imageService(ImageRepository imageRepository) {
        return new ImageServiceImpl(imageRepository);
    }

    @Bean
    public DebugService debugService(ImageRepository imageRepository, DatasetService datasetService) {
        return new DebugServiceImpl(imageRepository, datasetService);
    }
}
