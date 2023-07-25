package com.rviewer.skeletons.infrastructure.config.sorter;

import com.rviewer.skeletons.domain.sorter.algorithm.impl.EventImageSorterAlgorithm;
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
    public EventImageSorterAlgorithm eventImageSorter() {
        return new EventImageSorterAlgorithm(eventSorter.getName(), eventSorter.getCriteria());
    }
}
