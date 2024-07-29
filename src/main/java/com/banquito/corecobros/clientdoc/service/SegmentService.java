package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Segment;
import com.banquito.corecobros.clientdoc.repository.SegmentRepository;
import com.banquito.corecobros.clientdoc.util.UniqueId.UniqueIdGeneration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SegmentService {

    private final SegmentRepository repository;
    private final UniqueIdGeneration uniqueIdGeneration;
    private static final String CLIENT_SEGMENT_NOT_FOUND = "No existe el segmento con código: ";

    public SegmentService(SegmentRepository repository, UniqueIdGeneration uniqueIdGeneration) {
        this.repository = repository;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public List<Segment> getAllSegments() {
        log.info("Obteniendo todos los segmentos ordenados por nombre.");
        return repository.findAllByOrderByName();
    }

    public Segment getSegmentByCode(String code) {
        log.info("Buscando segmento con código: {}", code);
        Segment segment = repository.findById(code)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + code);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + code);
                });
        return segment;
    }

    public Segment createSegment(Segment segment) {
        log.info("Creando nuevo segmento con código: {}", segment.getCode());
        segment.setUniqueId(uniqueIdGeneration.generateUniqueId());
        return repository.save(segment);
    }

    public Segment updateSegment(String code, Segment segmentDetails) {
        log.info("Actualizando segmento con código: {}", code);
        Segment segment = repository.findById(code)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + code);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + code);
                });

        segment.setName(segmentDetails.getName());
        segment.setClientType(segmentDetails.getClientType());
        segment.setDescription(segmentDetails.getDescription());

        log.info("Segmento con código: {} actualizado exitosamente", code);
        return repository.save(segment);
    }

    public void deleteSegment(String code) {
        log.info("Eliminando segmento con código: {}", code);
        Segment segment = repository.findById(code)
                .orElseThrow(() -> {
                    log.error(CLIENT_SEGMENT_NOT_FOUND + code);
                    return new RuntimeException(CLIENT_SEGMENT_NOT_FOUND + code);
                });

        repository.delete(segment);
        log.info("Segmento con código: {} eliminado exitosamente", code);
    }
}
