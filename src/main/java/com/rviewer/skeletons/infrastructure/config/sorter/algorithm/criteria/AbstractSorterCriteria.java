package com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractSorterCriteria {

    @NotBlank
    private String direction;

}
