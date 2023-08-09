package com.rviewer.skeletons.infrastructure.config.sorter;

import com.rviewer.skeletons.domain.algorithm.impl.EventImageSorterAlgorithm;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.sorter.EventSorterService;
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.EventSorterConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "sorter.sorter-list")
public class SorterList {

    private EventSorterConfig eventSorter;

    @Bean
    public AbstractSorterService eventSorterService(ImageRepository imageRepository) {
        return new EventSorterService(
                imageRepository,
                new EventImageSorterAlgorithm(eventSorter.getName(), eventSorter.getDirection()),
                eventSorter.getCriteria().getView(),
                eventSorter.getCriteria().getClick()
        );
    }
}
