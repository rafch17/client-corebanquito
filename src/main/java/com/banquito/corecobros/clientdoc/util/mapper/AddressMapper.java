package com.banquito.corecobros.clientdoc.util.mapper;

import com.banquito.corecobros.clientdoc.dto.AddressDTO;
import com.banquito.corecobros.clientdoc.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(Address address);

    Address toModel(AddressDTO addressDTO);
}