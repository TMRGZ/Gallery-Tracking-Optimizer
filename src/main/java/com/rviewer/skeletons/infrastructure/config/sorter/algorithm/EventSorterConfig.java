package com.rviewer.skeletons.infrastructure.config.sorter.algorithm;

import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.EventSorterCriteria;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSorterConfig extends AbstractSorterAlgorithmConfig {

    @NotNull
    private EventSorterCriteria criteria;

}
