package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.model.Segment;
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

    @GetMapping
    public List<Segment> getAllSegments() {
        log.info("Obteniendo todos los segmentos ordenados por nombre.");
        return segmentService.getAllSegments();
    }

    @GetMapping("/{code}")
    public Segment getSegmentByCode(@PathVariable String code) {
        log.info("Buscando segmento con código: {}", code);
        return segmentService.getSegmentByCode(code);
    }

    @PostMapping
    public Segment createSegment(@RequestBody Segment segment) {
        log.info("Creando nuevo segmento con código: {}", segment.getCode());
        return segmentService.createSegment(segment);
    }

    @PutMapping("/{code}")
    public Segment updateSegment(@PathVariable String code, @RequestBody Segment segment) {
        log.info("Actualizando segmento con código: {}", code);
        segment.setCode(code);
        return segmentService.updateSegment(code, segment);
    }

    @DeleteMapping("/{code}")
    public void deleteSegment(@PathVariable String code) {
        log.info("Eliminando segmento con código: {}", code);
        segmentService.deleteSegment(code);
    }
}
