package com.banquito.core.clientdoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SegmentDTO {
    private String id;
    private String code;
    private String name;
    private String clientType;
    private String description;
}
