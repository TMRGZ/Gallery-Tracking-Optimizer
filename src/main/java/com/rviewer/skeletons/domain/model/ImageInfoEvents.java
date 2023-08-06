package com.rviewer.skeletons.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
public class ImageInfoEvents implements Serializable {

    private BigDecimal views;

    private BigDecimal clicks;
}
