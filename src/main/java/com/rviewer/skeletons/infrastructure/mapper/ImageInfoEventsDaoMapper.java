package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageInfoEventsDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageInfoEventsDaoMapper {

    ImageInfoEventsDao map(ImageInfoEvents imageInfoEvents);

    ImageInfoEvents map(ImageInfoEventsDao imageInfoEventsDao);

}
