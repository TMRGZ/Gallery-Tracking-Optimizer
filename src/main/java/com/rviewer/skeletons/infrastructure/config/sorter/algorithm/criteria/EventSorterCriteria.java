package com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSorterCriteria extends AbstractSorterCriteria {

    @NotNull
    private Long view;

    @NotNull
    private Long click;

}
