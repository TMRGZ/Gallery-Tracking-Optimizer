package com.rviewer.skeletons.application.mapper;

import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto;
import com.rviewer.skeletons.domain.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    Event map(TrackEventBodyDto trackEventBodyDto);

}
