package com.banquito.corecobros.clientdoc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
    private String id;
    private String clientId;
    private String type;
    private String number;
    private Boolean isDefault;
    private String state;
}
