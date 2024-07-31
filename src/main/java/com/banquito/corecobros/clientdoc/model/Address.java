package com.banquito.corecobros.clientdoc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

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
