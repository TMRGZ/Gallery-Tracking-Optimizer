package com.rviewer.skeletons.domain.factory;

import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;

public interface SorterFactory {

    AbstractSorterService getSorter(String name);
}
