package com.rviewer.skeletons.infrastructure.config.sorter.algorithm;

import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseSorterAlgorithmConfig {

    @NotBlank
    private String name;

    @NotNull
    private SortDirectionEnum direction;

}
