package com.rviewer.skeletons.infrastructure.config.sorter;

import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import com.rviewer.skeletons.infrastructure.exception.InvalidConfigurationException;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "sorter.config")
public class SorterConfig {

    @NotBlank
    private String defaultAlgorithm;

    @Bean
    public AbstractSorterService defaultSorter(List<AbstractSorterService> imageSorterList) {
        return getSorter(imageSorterList, defaultAlgorithm);
    }

    private AbstractSorterService getSorter(List<AbstractSorterService> sorterList, String algorithm) {
        return sorterList.stream()
                .filter(genericImageSorter -> genericImageSorter.getAlgorithmName().equalsIgnoreCase(algorithm))
                .findAny()
                .orElseThrow(() -> new InvalidConfigurationException("Sorter with %s algorithm not found".formatted(algorithm)));
    }
}
