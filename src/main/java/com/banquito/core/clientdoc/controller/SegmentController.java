package com.banquito.core.clientdoc.controller;

import com.banquito.core.clientdoc.model.Segment;
import com.banquito.core.clientdoc.service.SegmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/segment")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping
    public List<Segment> getAllSegments() {
        return segmentService.getAllSegments();
    }

    @GetMapping("/{code}")
    public Segment getSegmentByCode(@PathVariable String code) {
        return segmentService.getSegmentByCode(code);
    }

    @PostMapping
    public Segment createSegment(@RequestBody Segment segment) {
        return segmentService.createSegment(segment);
    }

    @PutMapping("/{code}")
    public Segment updateSegment(@PathVariable String code, @RequestBody Segment segment) {
        segment.setCode(code);
        return segmentService.updateSegment(code, segment);
    }

    @DeleteMapping("/{code}")
    public void deleteSegment(@PathVariable String code) {
        segmentService.deleteSegment(code);
    }
}
