package com.banquito.corecobros.clientdoc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ClientDTO {
    private String id;
    private String codeSegment;
    private String nameSegment;
    private String uniqueId;
    private String clientType;
    private String identificationType;
    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String companyName;
    private String companyType;
    private String state;
    private LocalDateTime createDate;
    private LocalDateTime lastStateDate;
    private String nationality;
    private String maritalState;
    private BigDecimal monthlyAverageIncome;
    private String notes;
    private List<PhoneDTO> phones;
    private List<AddressDTO> addresses;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
