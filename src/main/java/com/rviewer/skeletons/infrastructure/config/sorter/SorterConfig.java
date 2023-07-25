package com.rviewer.skeletons.infrastructure.config.sorter;

import com.rviewer.skeletons.domain.sorter.algorithm.GenericImageSorterAlgorithm;
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
    public GenericImageSorterAlgorithm defaultSorter(List<GenericImageSorterAlgorithm> imageSorterList) {
        return getSorter(imageSorterList, defaultAlgorithm);
    }

    private GenericImageSorterAlgorithm getSorter(List<GenericImageSorterAlgorithm> sorterList, String algorithm) {
        return sorterList.stream()
                .filter(genericImageSorter -> genericImageSorter.getImageSorterName().equalsIgnoreCase(algorithm))
                .findAny().orElseThrow();
    }
}
