package com.banquito.corecobros.clientdoc.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
    private String id;
    @NotNull
    private String clientId;
    @NotNull
    private String type;
    @NotNull
    private String number;
    @NotNull
    private Boolean isDefault;
    @NotNull
    private String state;
}
