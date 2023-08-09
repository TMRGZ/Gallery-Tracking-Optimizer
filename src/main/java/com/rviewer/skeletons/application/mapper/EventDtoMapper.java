package com.rviewer.skeletons.application.mapper;

import com.rviewer.skeletons.domain.model.Event;
import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    Event map(TrackEventBodyDto trackEventBodyDto);

}
