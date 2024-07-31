package com.banquito.corecobros.clientdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class AddressDTO {
    private String id;
    @NotNull
    private String type;
    @NotNull
    private String line1;
    @NotNull
    private String line2;
    @NotBlank
    private Float latitude;
    @NotBlank
    private Float longitude;
    @NotBlank
    private Boolean isDefault;
    @NotNull
    private String state;
}