package com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DefaultSorterCriteria extends AbstractSorterCriteria {

    @NotBlank
    private String field;

}
