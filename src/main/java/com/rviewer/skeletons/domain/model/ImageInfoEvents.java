package com.rviewer.skeletons.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ImageInfoEvents {

    private BigDecimal views;

    private BigDecimal clicks;
}
