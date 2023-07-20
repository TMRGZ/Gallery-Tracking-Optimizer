package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.infrastructure.persistence.dao.ImageDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageDaoMapper {

    ImageDao map(Image image);

    Image map(ImageDao imageDao);
}
