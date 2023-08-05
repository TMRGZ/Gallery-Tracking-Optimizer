package com.rviewer.skeletons.application.mapper;

import generated.com.rviewer.skeletons.application.model.ImageInfoEventsDto;
import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageInfoEventsDtoMapper {

    ImageInfoEvents map(ImageInfoEventsDto imageInfoEventsDto);

    ImageInfoEventsDto map(ImageInfoEvents imageInfoEvents);

}
