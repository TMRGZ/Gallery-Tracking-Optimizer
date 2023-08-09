package com.rviewer.skeletons.domain.algorithm;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor
public abstract class ImageSorterAlgorithm implements Comparator<Image> {

    private final String algorithmName;

    private final SortDirectionEnum direction;

    @Override
    public int compare(Image o1, Image o2) {
        return 0;
    }

    public Comparator<Image> fallbackCompare() {
        return Comparator.comparing(Image::getCreatedAt).reversed();
    }
}
