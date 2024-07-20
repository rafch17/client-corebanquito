package com.banquito.core.clientdoc.util.mapper;

import com.banquito.core.clientdoc.dto.SegmentDTO;
import com.banquito.core.clientdoc.model.Segment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SegmentMapper {
    SegmentMapper INSTANCE = Mappers.getMapper(SegmentMapper.class);

    SegmentDTO toDTO(Segment segment);

    Segment toModel(SegmentDTO segmentDTO);
}
