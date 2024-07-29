package com.banquito.corecobros.clientdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PhoneDTO {
    private String id;
    private String clientId;
    private String phoneType;
    private String phoneNumber;
    private Boolean isDefault;
    private Boolean state;
}
