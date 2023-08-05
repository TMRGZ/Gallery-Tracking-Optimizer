package com.rviewer.skeletons.application.mapper;

import generated.com.rviewer.skeletons.application.model.ImageInfoEventsDto;
import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageInfoEventsDtoMapper {

    ImageInfoEventsDto map(ImageInfoEvents imageInfoEvents);

}
