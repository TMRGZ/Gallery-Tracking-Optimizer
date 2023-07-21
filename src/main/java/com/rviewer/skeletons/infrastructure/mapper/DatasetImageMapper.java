package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.infrastructure.rest.dataset.model.DatasetImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DatasetImageMapper {

    Image map(DatasetImageDto datasetImageDto);

}
