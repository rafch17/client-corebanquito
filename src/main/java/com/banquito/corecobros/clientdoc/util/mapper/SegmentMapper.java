package com.banquito.corecobros.clientdoc.util.mapper;

import com.banquito.corecobros.clientdoc.dto.SegmentDTO;
import com.banquito.corecobros.clientdoc.model.Segment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SegmentMapper {
    SegmentMapper INSTANCE = Mappers.getMapper(SegmentMapper.class);

    SegmentDTO toDTO(Segment segment);

    Segment toModel(SegmentDTO segmentDTO);
}
