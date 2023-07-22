package com.rviewer.skeletons.infrastructure.config.sorter;

import com.rviewer.skeletons.domain.sorter.impl.DefaultImageSorter;
import com.rviewer.skeletons.domain.sorter.impl.EventImageSorter;
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.DefaultSorterConfig;
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
@ConfigurationProperties(prefix = "sorter")
public class SorterConfig {

    private EventSorterConfig eventSorter;

    private DefaultSorterConfig defaultSorter;

    @Bean
    public EventImageSorter eventImageSorter() {
        return new EventImageSorter(eventSorter.getName());
    }

    @Bean
    public DefaultImageSorter defaultImageSorter() {
        return new DefaultImageSorter(
                defaultSorter.getName(),
                defaultSorter.getCriteria().getDirection(),
                defaultSorter.getCriteria().getField()
        );
    }
}
