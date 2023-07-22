package com.rviewer.skeletons.infrastructure.config.sorter.algorithm;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractSorterAlgorithmConfig {

    @NotBlank
    private String name;

}
