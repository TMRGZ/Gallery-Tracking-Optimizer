package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.infrastructure.persistence.dao.EventDao;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EventDaoMapper {

    EventDao map(Event image);

    Event map(EventDao imageDao);

    default OffsetDateTime map(Instant instant) {
        return Optional.ofNullable(instant)
                .map(i -> OffsetDateTime.ofInstant(i, ZoneId.systemDefault()))
                .orElse(null);
    }

    default Instant map(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
                .map(OffsetDateTime::toInstant)
                .orElse(null);
    }
}
