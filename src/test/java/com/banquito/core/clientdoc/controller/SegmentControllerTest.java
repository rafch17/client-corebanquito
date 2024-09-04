package com.banquito.core.clientdoc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.banquito.core.clientdoc.dto.SegmentDTO;
import com.banquito.core.clientdoc.service.SegmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SegmentController.class)
public class SegmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SegmentService segmentService;

    private ObjectMapper objectMapper;
    private SegmentDTO segmentDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        segmentDTO = new SegmentDTO("1", "SEGCOD001", "MUR0057262", "NATURALES", "PER", "personas natuales");
    }

    @Test
    void testGetAllSegments() throws Exception {
        List<SegmentDTO> segments = Collections.singletonList(segmentDTO);
        when(segmentService.getAllSegments()).thenReturn(segments);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client-microservice/api/v1/segments")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("NATURALES"));

        verify(segmentService, times(1)).getAllSegments();
    }

        @Test
    void testGetSegmentByUniqueId() throws Exception {
        when(segmentService.getSegmentByUniqueId(anyString())).thenReturn(segmentDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client-microservice/api/v1/segments/MUR0057262")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NATURALES"));

        verify(segmentService, times(1)).getSegmentByUniqueId("MUR0057262");
    }

    @Test
    void testCreateSegment() throws Exception {
        when(segmentService.createSegment(any(SegmentDTO.class))).thenReturn(segmentDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/client-microservice/api/v1/segments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(segmentDTO)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NATURALES"));

        verify(segmentService, times(1)).createSegment(any(SegmentDTO.class));
    }

    @Test
    void testUpdateSegment() throws Exception {
        when(segmentService.updateSegment(anyString(), any(SegmentDTO.class))).thenReturn(segmentDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/client-microservice/api/v1/segments/MUR0057262")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(segmentDTO)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NATURALES"));

        verify(segmentService, times(1)).updateSegment(eq("MUR0057262"), any(SegmentDTO.class));
    }

    @Test
    void testDeleteSegment() throws Exception {
        doNothing().when(segmentService).deleteSegment(anyString());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/client-microservice/api/v1/segments/MUR0057262")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());

        verify(segmentService, times(1)).deleteSegment("MUR0057262");
    }


}
