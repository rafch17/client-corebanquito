package com.banquito.core.clientdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banquito.core.clientdoc.dto.SegmentDTO;
import com.banquito.core.clientdoc.model.Segment;
import com.banquito.core.clientdoc.repository.SegmentRepository;
import com.banquito.core.clientdoc.util.mapper.SegmentMapper;

public class SegmentServiceTest {
    @Mock
    private SegmentRepository repository;

    @Mock
    private SegmentMapper segmentMapper;

    @InjectMocks
    private SegmentService segmentService;

    private Segment segment;
    private SegmentDTO segmentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    
        segment = new Segment();
        segment.setId("1");
        segment.setCode("SEGCOD001");
        segment.setUniqueId("MUR0057262");
        segment.setName("NATURALES");
        segment.setClientType("PER");
        segment.setDescription("personas natuales");
        
        segmentDTO = new SegmentDTO("1", "SEGCOD001", "MUR0057262", "NATURALES", "PER", "personas natuales");
    }

    @Test
    void testGetAllSegments() {
        when(repository.findAllByOrderByName()).thenReturn(Collections.singletonList(segment));
        when(segmentMapper.toDTO(any(Segment.class))).thenReturn(segmentDTO);

        List<SegmentDTO> segmentDTOs = segmentService.getAllSegments();

        assertEquals(1, segmentDTOs.size());
        assertEquals(segmentDTO, segmentDTOs.get(0));
        verify(repository, times(1)).findAllByOrderByName();
    }

    @Test
    void testGetSegmentByUniqueId_Success() {
        when(repository.findByUniqueId(anyString())).thenReturn(segment);
        when(segmentMapper.toDTO(any(Segment.class))).thenReturn(segmentDTO);

        SegmentDTO result = segmentService.getSegmentByUniqueId("MUR0057262");

        assertEquals(segmentDTO, result);
        verify(repository, times(1)).findByUniqueId("MUR0057262");
    }

    @Test
    void testGetSegmentByUniqueId_NotFound() {
        when(repository.findByUniqueId(anyString())).thenReturn(null);

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                segmentService.getSegmentByUniqueId("1111")
        );

        assertEquals("No existe el segmento con uniqueId: 1111", thrown.getMessage());
        verify(repository, times(1)).findByUniqueId("1111");
    }

    @Test
    void testCreateSegment() {
        when(segmentMapper.toModel(any(SegmentDTO.class))).thenReturn(segment);
        when(repository.save(any(Segment.class))).thenReturn(segment);
        when(segmentMapper.toDTO(any(Segment.class))).thenReturn(segmentDTO);

        SegmentDTO result = segmentService.createSegment(segmentDTO);

        assertEquals(segmentDTO, result);
        verify(segmentMapper, times(1)).toModel(segmentDTO);
        verify(repository, times(1)).save(segment);
        verify(segmentMapper, times(1)).toDTO(segment);
    }

    @Test
    void testUpdateSegment_Success() {
        when(repository.findByUniqueId(anyString())).thenReturn(segment);
        when(repository.save(any(Segment.class))).thenReturn(segment);
        when(segmentMapper.toDTO(any(Segment.class))).thenReturn(segmentDTO);

        SegmentDTO result = segmentService.updateSegment("MUR0057262", segmentDTO);

        assertEquals(segmentDTO, result);
        verify(repository, times(1)).findByUniqueId("MUR0057262");
        verify(repository, times(1)).save(segment);
    }

    @Test
    void testUpdateSegment_NotFound() {
        when(repository.findByUniqueId(anyString())).thenReturn(null);

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                segmentService.updateSegment("1111", segmentDTO)
        );

        assertEquals("No existe el segmento con uniqueId: 1111", thrown.getMessage());
        verify(repository, times(1)).findByUniqueId("1111");
    }

    @Test
    void testDeleteSegment_Success() {
        when(repository.findByUniqueId(anyString())).thenReturn(segment);

        segmentService.deleteSegment("MUR0057262");

        verify(repository, times(1)).findByUniqueId("MUR0057262");
        verify(repository, times(1)).delete(segment);
    }

    @Test
    void testDeleteSegment_NotFound() {
        when(repository.findByUniqueId(anyString())).thenReturn(null);

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                segmentService.deleteSegment("1111")
        );

        assertEquals("No existe el segmento con uniqueId: 1111", thrown.getMessage());
        verify(repository, times(1)).findByUniqueId("1111");
    }
}
