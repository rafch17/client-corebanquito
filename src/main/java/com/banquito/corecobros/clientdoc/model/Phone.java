package com.banquito.corecobros.clientdoc.model;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
    @Id
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
