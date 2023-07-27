package com.rviewer.skeletons.infrastructure.persistence.dao;

import com.rviewer.skeletons.domain.model.enums.EventTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Document("event")
public class EventDao {

    private UUID id;

    private UUID imageId;

    private EventTypeEnum eventType;

    private Instant timestamp;

}
