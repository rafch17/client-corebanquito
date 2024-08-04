package com.banquito.core.clientdoc.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PhoneDTO {
    private String id;
    @NotNull
    private String type;
    @NotNull
    private String number;
    @NotNull
    private Boolean isDefault;
    @NotNull
    private String state;
}