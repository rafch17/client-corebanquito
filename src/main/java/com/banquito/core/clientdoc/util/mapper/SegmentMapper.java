package com.banquito.core.clientdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.clientdoc.dto.SegmentDTO;
import com.banquito.core.clientdoc.model.Segment;

@Mapper(componentModel = "spring")
public interface SegmentMapper {
    SegmentDTO toDTO(Segment segment);

    Segment toModel(SegmentDTO segmentDTO);
}