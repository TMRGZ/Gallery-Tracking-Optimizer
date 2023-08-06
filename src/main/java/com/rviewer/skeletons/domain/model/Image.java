package com.rviewer.skeletons.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class Image implements Serializable {

    private UUID id;

    private String name;

    private String url;

    private OffsetDateTime createdAt;

    private BigDecimal weight;

    private BigDecimal gridPosition;

    private ImageInfoEvents events;

}
