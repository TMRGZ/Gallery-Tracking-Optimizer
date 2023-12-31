package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@Document("image")
public class ImageDao {

    private UUID id;

    private String name;

    private String url;

    private Instant createdAt;

    private ImageInfoEventsDao events;

}
