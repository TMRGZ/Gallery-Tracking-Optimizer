package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Document
public class ImageDao {

    private UUID id;

    private String name;

    private String url;

    private OffsetDateTime createdAt;

}
