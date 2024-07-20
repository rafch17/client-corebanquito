package com.banquito.core.clientdoc.util.mapper;

import com.banquito.core.clientdoc.dto.PhoneDTO;
import com.banquito.core.clientdoc.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    @Mapping(source = "type", target = "phoneType")
    @Mapping(source = "number", target = "phoneNumber")
    PhoneDTO toDTO(Phone phone);

    @Mapping(source = "phoneType", target = "type")
    @Mapping(source = "phoneNumber", target = "number")
    Phone toModel(PhoneDTO phoneDTO);
}
