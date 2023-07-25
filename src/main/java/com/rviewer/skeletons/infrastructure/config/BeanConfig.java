package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.debug.DebugService;
import com.rviewer.skeletons.domain.service.debug.impl.DebugServiceImpl;
import com.rviewer.skeletons.domain.service.event.EventService;
import com.rviewer.skeletons.domain.service.event.impl.EventServiceImpl;
import com.rviewer.skeletons.domain.service.image.ImageService;
import com.rviewer.skeletons.domain.service.image.impl.ImageServiceImpl;
import com.rviewer.skeletons.domain.sorter.algorithm.GenericImageSorterAlgorithm;
import com.rviewer.skeletons.domain.sorter.factory.SorterFactory;
import com.rviewer.skeletons.domain.sorter.factory.impl.SorterFactoryImpl;
import com.rviewer.skeletons.infrastructure.config.sorter.SorterConfig;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import(SorterConfig.class)
public class BeanConfig {

    @Bean
    public SorterFactory sorterFactory(List<GenericImageSorterAlgorithm> imageSorterList, SorterConfig sorterConfig) {
        return new SorterFactoryImpl(imageSorterList, sorterConfig.getDefaultAlgorithm());
    }

    @Bean
    public ImageService imageService(ImageRepository imageRepository, SorterFactory sorterFactory) {
        return new ImageServiceImpl(imageRepository, sorterFactory);
    }

    @Bean
    public EventService eventService(EventRepository eventRepository, ImageService imageService) {
        return new EventServiceImpl(eventRepository, imageService);
    }

    @Bean
    public DebugService debugService(ImageService imageService, DatasetService datasetService) {
        return new DebugServiceImpl(imageService, datasetService);
    }
}
