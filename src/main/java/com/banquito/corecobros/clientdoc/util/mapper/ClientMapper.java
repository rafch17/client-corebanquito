package com.banquito.corecobros.clientdoc.util.mapper;

import com.banquito.corecobros.clientdoc.dto.SimpleClientDTO;
import com.banquito.corecobros.clientdoc.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
        ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

        SimpleClientDTO toSimpleDTO(Client client);

        Client toModel(SimpleClientDTO clientDTO);
}
