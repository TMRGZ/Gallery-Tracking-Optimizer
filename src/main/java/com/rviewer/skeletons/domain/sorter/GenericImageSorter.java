package com.rviewer.skeletons.domain.sorter;

import com.rviewer.skeletons.domain.model.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor
public abstract class GenericImageSorter implements Comparator<Image> {

    private final String imageSorterName;


}
