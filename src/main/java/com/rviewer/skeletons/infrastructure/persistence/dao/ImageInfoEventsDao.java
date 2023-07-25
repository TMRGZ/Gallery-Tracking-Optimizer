package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ImageInfoEventsDao {

    static final ImageInfoEventsDao emptyEvents = ImageInfoEventsDao.builder()
            .views(BigDecimal.ZERO)
            .clicks(BigDecimal.ZERO)
            .build();

    private BigDecimal views;

    private BigDecimal clicks;

}
