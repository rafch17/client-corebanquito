package com.banquito.core.clientdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.clientdoc.dto.AddressDTO;
import com.banquito.core.clientdoc.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(Address address);

    Address toModel(AddressDTO addressDTO);
}