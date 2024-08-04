package com.banquito.core.clientdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.clientdoc.dto.PhoneDTO;
import com.banquito.core.clientdoc.model.Phone;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneDTO toDTO(Phone phone);

    Phone toModel(PhoneDTO phoneDTO);
}