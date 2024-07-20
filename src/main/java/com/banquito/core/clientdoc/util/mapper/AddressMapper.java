package com.banquito.core.clientdoc.util.mapper;

import com.banquito.core.clientdoc.dto.AddressDTO;
import com.banquito.core.clientdoc.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "type", target = "typeAddress")
    AddressDTO toDTO(Address address);

    @Mapping(source = "typeAddress", target = "type")
    Address toModel(AddressDTO addressDTO);
}
