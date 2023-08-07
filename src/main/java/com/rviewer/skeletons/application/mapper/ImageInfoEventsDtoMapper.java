package com.rviewer.skeletons.application.mapper;

import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import generated.com.rviewer.skeletons.application.model.ImageInfoEventsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageInfoEventsDtoMapper {

    ImageInfoEventsDto map(ImageInfoEvents imageInfoEvents);

}
