package com.rviewer.skeletons.domain.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SortDirectionEnum {
    ASC("ASC"), DESC("DESC");

    private final String value;

    public static SortDirectionEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(sortDirectionEnum -> sortDirectionEnum.value.equalsIgnoreCase(value))
                .findAny().orElseThrow();
    }
}
