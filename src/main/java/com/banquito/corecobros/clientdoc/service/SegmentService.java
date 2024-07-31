package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Segment;
import com.banquito.corecobros.clientdoc.repository.SegmentRepository;
import com.banquito.corecobros.clientdoc.util.UniqueId.UniqueIdGeneration;
import com.banquito.corecobros.clientdoc.dto.SegmentDTO;
import com.banquito.corecobros.clientdoc.util.mapper.SegmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SegmentService {

    private final SegmentRepository repository;
    private final UniqueIdGeneration uniqueIdGeneration;
    private final SegmentMapper segmentMapper;
    private static final String CLIENT_SEGMENT_NOT_FOUND = "No existe el segmento con código: ";

    public SegmentService(SegmentRepository repository, UniqueIdGeneration uniqueIdGeneration,
            SegmentMapper segmentMapper) {
        this.repository = repository;
        this.uniqueIdGeneration = uniqueIdGeneration;
        this.segmentMapper = segmentMapper;
    }

    public List<SegmentDTO> getAllSegments() {
        log.info("Obteniendo todos los segmentos.");
        return repository.findAllByOrderByName().stream()
                .map(segmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SegmentDTO getSegmentByUniqueId(String uniqueId) {
        log.info("Buscando segmento con uniqueId: {}", uniqueId);
        Segment segment = repository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                });
        return segmentMapper.toDTO(segment);
    }

    public SegmentDTO createSegment(SegmentDTO segmentDTO) {
        log.info("Creando nuevo segmento.");
        Segment segment = segmentMapper.toModel(segmentDTO);
        segment.setUniqueId(uniqueIdGeneration.generateUniqueId());
        return segmentMapper.toDTO(repository.save(segment));
    }

    public SegmentDTO updateSegment(String uniqueId, SegmentDTO segmentDTO) {
        log.info("Actualizando segmento con uniqueId: {}", uniqueId);
        Segment segment = repository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                });
        segment.setName(segmentDTO.getName());
        segment.setClientType(segmentDTO.getClientType());
        segment.setDescription(segmentDTO.getDescription());
        return segmentMapper.toDTO(repository.save(segment));
    }

    public void deleteSegment(String uniqueId) {
        log.info("Eliminando segmento con uniqueId: {}", uniqueId);
        Segment segment = repository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + uniqueId);
                });
        repository.delete(segment);
    }
}
