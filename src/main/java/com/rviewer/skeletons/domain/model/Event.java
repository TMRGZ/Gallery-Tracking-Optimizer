package com.rviewer.skeletons.domain.model;

import com.rviewer.skeletons.domain.model.enums.EventTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class Event {

    private UUID id;

    private UUID imageId;

    private EventTypeEnum eventType;

    private OffsetDateTime timestamp;

}
