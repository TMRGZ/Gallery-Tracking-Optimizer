package com.rviewer.skeletons.domain.sorter.algorithm;

import com.rviewer.skeletons.domain.model.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor
public abstract class GenericImageSorterAlgorithm implements Comparator<Image> {

    private final String imageSorterName;

    @Override
    public int compare(Image o1, Image o2) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
    }
}
