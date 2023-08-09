package com.rviewer.skeletons.application.mapper;

import com.rviewer.skeletons.domain.model.Image;
import generated.com.rviewer.skeletons.application.model.ImageInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ImageInfoEventsDtoMapper.class)
public interface ImageDtoMapper {

    ImageInfoDto map(Image image);

}
