package com.banquito.core.clientdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.banquito.core.clientdoc.dto.ClientDTO;
import com.banquito.core.clientdoc.model.Client;

@Mapper(componentModel = "spring", uses = { PhoneMapper.class, AddressMapper.class })
public interface ClientMapper {
        @Mapping(source = "phones", target = "phones")
        @Mapping(source = "addresses", target = "addresses")
        ClientDTO toDTO(Client client);

        @Mapping(source = "phones", target = "phones")
        @Mapping(source = "addresses", target = "addresses")
        Client toModel(ClientDTO clientDTO);
}
