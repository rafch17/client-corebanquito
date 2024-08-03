package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.SegmentDTO;
import com.banquito.corecobros.clientdoc.service.SegmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client-microservice/api/v1/segments")
@CrossOrigin(origins = "*")
@Tag(name = "Segment", description = "Endpoints for managing segments")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping
    @Operation(summary = "Get all segments", description = "Retrieve a list of all segments")
    public List<SegmentDTO> getAllSegments() {
        log.info("Obteniendo todos los segmentos.");
        return segmentService.getAllSegments();
    }

    @GetMapping("/{uniqueId}")
    @Operation(summary = "Get segment by uniqueId", description = "Retrieve a segment by its uniqueId")
    public SegmentDTO getSegmentByUniqueId(@PathVariable String uniqueId) {
        log.info("Buscando segmento con uniqueId: {}", uniqueId);
        return segmentService.getSegmentByUniqueId(uniqueId);
    }

    @PostMapping
    @Operation(summary = "Create a new segment", description = "Create a new segment")
    public SegmentDTO createSegment(@RequestBody SegmentDTO segmentDTO) {
        log.info("Creando nuevo segmento.");
        return segmentService.createSegment(segmentDTO);
    }

    @PutMapping("/{uniqueId}")
    @Operation(summary = "Update a segment", description = "Update an existing segment by its uniqueId")
    public SegmentDTO updateSegment(@PathVariable String uniqueId, @RequestBody SegmentDTO segmentDTO) {
        log.info("Actualizando segmento con uniqueId: {}", uniqueId);
        return segmentService.updateSegment(uniqueId, segmentDTO);
    }

    @DeleteMapping("/{uniqueId}")
    @Operation(summary = "Delete a segment", description = "Delete a segment by its uniqueId")
    public void deleteSegment(@PathVariable String uniqueId) {
        log.info("Eliminando segmento con uniqueId: {}", uniqueId);
        segmentService.deleteSegment(uniqueId);
    }
}
