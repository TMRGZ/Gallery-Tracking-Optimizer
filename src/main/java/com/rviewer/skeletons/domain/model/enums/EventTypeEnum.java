package com.rviewer.skeletons.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EventTypeEnum {
    VIEW("VIEW"), CLICK("CLICK");

    private final String value;

    public static EventTypeEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(eventTypeEnum -> eventTypeEnum.value.equalsIgnoreCase(value))
                .findAny().orElseThrow();
    }
}
