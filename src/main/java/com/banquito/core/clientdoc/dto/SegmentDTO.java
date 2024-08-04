package com.banquito.core.clientdoc.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SegmentDTO {
    private String id;
    @NotNull
    private String code;
    @NotNull
    private String uniqueId;
    @NotNull
    private String name;
    @NotEmpty
    private String clientType;
    private String description;
}