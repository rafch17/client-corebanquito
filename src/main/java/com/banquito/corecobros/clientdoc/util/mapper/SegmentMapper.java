package com.banquito.corecobros.clientdoc.util.mapper;

import com.banquito.corecobros.clientdoc.dto.SegmentDTO;
import com.banquito.corecobros.clientdoc.model.Segment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SegmentMapper {
    SegmentDTO toDTO(Segment segment);

    Segment toModel(SegmentDTO segmentDTO);
}