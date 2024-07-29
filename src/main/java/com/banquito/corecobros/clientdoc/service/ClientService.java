package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Client;
import com.banquito.corecobros.clientdoc.repository.ClientRepository;
import com.banquito.corecobros.clientdoc.dto.SimpleClientDTO;
import com.banquito.corecobros.clientdoc.util.UniqueId.UniqueIdGeneration;
import com.banquito.corecobros.clientdoc.util.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UniqueIdGeneration uniqueIdGeneration;
    private static final String CLIENT_NOT_FOUND = "No existe el cliente con id: ";

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper,
            UniqueIdGeneration uniqueIdGeneration) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public SimpleClientDTO getClientById(String id) {
        log.info("Buscando cliente con id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_NOT_FOUND + id);
                });
        return clientMapper.toSimpleDTO(client);
    }

    public SimpleClientDTO getClientByIdentification(String identification) {
        log.info("Buscando cliente con identificación: {}", identification);
        Client client = clientRepository.findByIdentification(identification);
        if (client != null) {
            log.info("Se encontró el cliente con identificación: {}", identification);
            return clientMapper.toSimpleDTO(client);
        } else {
            log.error("No existe el cliente con identificación: {}", identification);
            throw new RuntimeException("No existe el cliente con identificación: " + identification);
        }
    }

    public SimpleClientDTO getClientByEmail(String email) {
        log.info("Buscando cliente con email: {}", email);
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            log.info("Se encontró el cliente con email: {}", email);
            return clientMapper.toSimpleDTO(client);
        } else {
            log.error("No existe el cliente con email: {}", email);
            throw new RuntimeException("No existe el cliente con email: " + email);
        }
    }

    public SimpleClientDTO getClientByFullName(String fullName) {
        log.info("Buscando cliente con nombre completo: {}", fullName);
        Client client = clientRepository.findByFullName(fullName);
        if (client != null) {
            log.info("Se encontró el cliente con nombre completo: {}", fullName);
            return clientMapper.toSimpleDTO(client);
        } else {
            log.error("No existe el cliente con nombre completo: {}", fullName);
            throw new RuntimeException("No existe el cliente con nombre completo: " + fullName);
        }
    }

    public List<SimpleClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientRepository.findAll().stream()
                .map(clientMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }

    public SimpleClientDTO createClient(SimpleClientDTO clientDTO) {
        log.info("Creando nuevo cliente.");
        Client client = clientMapper.toModel(clientDTO);
        client.setUniqueId(uniqueIdGeneration.generateUniqueId());
        client = clientRepository.save(client);
        log.info("Cliente creado exitosamente con id: {}", client.getId());
        return clientMapper.toSimpleDTO(client);
    }

    public SimpleClientDTO updateClient(String id, SimpleClientDTO clientDetails) {
        log.info("Actualizando cliente con id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_NOT_FOUND + id);
                });

        client.setLastName(clientDetails.getLastName());
        client.setFirstName(clientDetails.getFirstName());
        client.setFullName(clientDetails.getFullName());
        client.setEmail(clientDetails.getEmail());
        client.setMaritalState(clientDetails.getMaritalState());
        client.setNationality(clientDetails.getNationality());
        client.setCompanyName(clientDetails.getCompanyName());
        client.setCompanyType(clientDetails.getCompanyType());
        client.setNotes(clientDetails.getNotes());
        client.setIdentificationType(clientDetails.getIdentificationType());
        client.setIdentification(clientDetails.getIdentification());
        client.setMonthlyAverageIncome(clientDetails.getMonthlyAverageIncome());

        client = clientRepository.save(client);
        log.info("Cliente con id: {} actualizado exitosamente", id);
        return clientMapper.toSimpleDTO(client);
    }

    @Transactional
    public void deleteClient(String id) {
        log.info("Eliminando cliente con id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_NOT_FOUND + id);
                });

        client.setState("INA");
        clientRepository.save(client);
        log.info("Cliente con id: {} eliminado exitosamente", id);
    }

    @Transactional
    public void reactivateClient(String id) {
        log.info("Reactivando cliente con id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_NOT_FOUND + id);
                });

        client.setState("ACT");
        clientRepository.save(client);
        log.info("Cliente con id: {} reactivado exitosamente", id);
    }

    public SimpleClientDTO getLastInsertedClient() {
        log.info("Obteniendo el último cliente insertado.");
        Client client = clientRepository.findTopByOrderByCreateDateDesc();
        if (client != null) {
            return clientMapper.toSimpleDTO(client);
        } else {
            log.error("No se encontró el último cliente insertado.");
            throw new RuntimeException("No se encontró el último cliente insertado.");
        }
    }

    public List<SimpleClientDTO> getClientsByIdentificationType(String identificationType) {
        log.info("Obteniendo clientes por tipo de identificación: {}", identificationType);
        return clientRepository.findByIdentificationType(identificationType).stream()
                .map(clientMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }

    public List<SimpleClientDTO> getClientsByCompanyName(String companyName) {
        log.info("Obteniendo clientes por nombre de la empresa: {}", companyName);
        return clientRepository.findByCompanyName(companyName).stream()
                .map(clientMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }
}
