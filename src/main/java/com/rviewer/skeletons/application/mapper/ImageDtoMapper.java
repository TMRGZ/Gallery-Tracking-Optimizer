package com.rviewer.skeletons.application.mapper;

import com.rviewer.skeletons.application.model.ImageInfoDto;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageDtoMapper {

    ImageInfoDto map(Image image);

}
