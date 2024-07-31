package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.SegmentDTO;
import com.banquito.corecobros.clientdoc.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/segment")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @CrossOrigin(origins = "*")

    @GetMapping
    public List<SegmentDTO> getAllSegments() {
        log.info("Obteniendo todos los segmentos.");
        return segmentService.getAllSegments();
    }

    @GetMapping("/{uniqueId}")
    public SegmentDTO getSegmentByUniqueId(@PathVariable String uniqueId) {
        log.info("Buscando segmento con uniqueId: {}", uniqueId);
        return segmentService.getSegmentByUniqueId(uniqueId);
    }

    @PostMapping
    public SegmentDTO createSegment(@RequestBody SegmentDTO segmentDTO) {
        log.info("Creando nuevo segmento.");
        return segmentService.createSegment(segmentDTO);
    }

    @PutMapping("/{uniqueId}")
    public SegmentDTO updateSegment(@PathVariable String uniqueId, @RequestBody SegmentDTO segmentDTO) {
        log.info("Actualizando segmento con uniqueId: {}", uniqueId);
        return segmentService.updateSegment(uniqueId, segmentDTO);
    }

    @DeleteMapping("/{uniqueId}")
    public void deleteSegment(@PathVariable String uniqueId) {
        log.info("Eliminando segmento con uniqueId: {}", uniqueId);
        segmentService.deleteSegment(uniqueId);
    }
}
