package com.rviewer.skeletons.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class Event {

    private UUID imageId;

    private String eventType;

    private OffsetDateTime timestamp;

}
