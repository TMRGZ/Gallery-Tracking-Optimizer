package com.rviewer.skeletons.infrastructure.config.sorter.algorithm;

import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.DefaultSorterCriteria;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultSorterConfig extends AbstractSorterAlgorithmConfig {

    @NotNull
    private DefaultSorterCriteria criteria;

}
