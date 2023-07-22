package com.rviewer.skeletons.infrastructure.persistence.dao;

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

    private String eventType;

    private Instant timestamp;

}
