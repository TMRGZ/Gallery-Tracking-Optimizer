package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ImageInfoEventsDao {

    @Builder.Default
    private BigDecimal views = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal clicks = BigDecimal.ZERO;

}
