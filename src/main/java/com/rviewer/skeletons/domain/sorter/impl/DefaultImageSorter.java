package com.rviewer.skeletons.domain.sorter.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.sorter.GenericImageSorter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Optional;


public class DefaultImageSorter extends GenericImageSorter {

    private final String sortDirection;

    private final String fieldToSort;

    public DefaultImageSorter(String imageSorterName, String sortDirection, String fieldToSort) {
        super(imageSorterName);
        this.sortDirection = sortDirection;
        this.fieldToSort = fieldToSort;
    }

    @Override
    public int compare(Image i1, Image i2) {
        Comparable valueToCompare1;
        Comparable valueToCompare2;

        try {
            valueToCompare1 = Optional.ofNullable(FieldUtils.readField(i1, fieldToSort, true))
                    .filter(Comparable.class::isInstance)
                    .map(Comparable.class::cast)
                    .orElseThrow();
            valueToCompare2 = Optional.ofNullable(FieldUtils.readField(i2, fieldToSort, true))
                    .filter(Comparable.class::isInstance)
                    .map(Comparable.class::cast)
                    .orElseThrow();

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return sortDirection.equals("desc")
                ? valueToCompare2.compareTo(valueToCompare1)
                : valueToCompare1.compareTo(valueToCompare2);
    }
}
