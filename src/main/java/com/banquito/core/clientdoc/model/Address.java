package com.banquito.core.clientdoc.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    @Id
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
