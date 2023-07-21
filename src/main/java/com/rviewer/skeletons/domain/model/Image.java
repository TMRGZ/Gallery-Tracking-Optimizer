package com.rviewer.skeletons.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class Image {

    private UUID id;

    private String name;

    private String url;

    private OffsetDateTime createdAt;

    private BigDecimal weight;

    private BigDecimal gridPosition;


}
