package com.banquito.corecobros.clientdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

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