package com.banquito.corecobros.clientdoc.util.mapper;

import com.banquito.corecobros.clientdoc.dto.PhoneDTO;
import com.banquito.corecobros.clientdoc.model.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneDTO toDTO(Phone phone);

    Phone toModel(PhoneDTO phoneDTO);
}