package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.ImageService;
import com.rviewer.skeletons.domain.service.impl.ImageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ImageService imageService(ImageRepository imageRepository) {
        return new ImageServiceImpl(imageRepository);
    }
}
