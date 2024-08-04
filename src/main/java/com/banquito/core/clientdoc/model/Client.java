package com.banquito.core.clientdoc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "clients")
@CompoundIndexes({
        @CompoundIndex(name = "cliidx_doctypedocU", def = "{'identificationType':1, 'identification':1}", unique = true),
        @CompoundIndex(name = "cliidx_lastfirstName", def = "{'lastName':1, 'firstName':1}")
})
public class Client {

    @Id
    private String id;
    @NotNull
    private String codeSegment;
    @NotNull
    private String nameSegment;
    @NotNull
    private String uniqueId;
    @NotNull
    private String clientType;
    @NotNull
    private String identificationType;
    @NotNull
    private String identification;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Indexed
    private String fullName;
    @Indexed(unique = true)
    @Email
    @NotNull
    private String email;
    private LocalDate birthDate;
    @NotNull
    private String companyName;
    @NotNull
    private String companyType;
    @NotNull
    private String state;
    @NotNull
    private LocalDateTime createDate;
    @NotNull
    private LocalDateTime lastStateDate;
    @NotNull
    private String nationality;
    @NotNull
    private String maritalState;
    @NotNull
    private BigDecimal monthlyAverageIncome;
    @NotNull
    private String notes;
    private List<Phone> phones;
    private List<Address> addresses;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
