package com.banquito.core.clientdoc.util.mapper;

import com.banquito.core.clientdoc.dto.SimpleClientDTO;
import com.banquito.core.clientdoc.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
        ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

        SimpleClientDTO toSimpleClientDTO(Client client);

        Client toClient(SimpleClientDTO clientDTO);
}
