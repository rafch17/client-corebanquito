package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.dto.ClientDTO;
import com.banquito.corecobros.clientdoc.model.Client;
import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.repository.ClientRepository;
import com.banquito.corecobros.clientdoc.util.UniqueId.UniqueIdGeneration;
import com.banquito.corecobros.clientdoc.util.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UniqueIdGeneration uniqueIdGeneration;
    private final ClientMapper clientMapper;
    private static final String CLIENT_NOT_FOUND = "No existe el cliente con uniqueId: ";

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper,
            UniqueIdGeneration uniqueIdGeneration) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public ClientDTO getClientByUniqueId(String uniqueId) {
        log.info("Buscando cliente con uniqueId: {}", uniqueId);
        Client client = clientRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_NOT_FOUND + uniqueId);
                });
        return clientMapper.toDTO(client);
    }

    public ClientDTO getClientByIdentification(String identification) {
        log.info("Buscando cliente con identificación: {}", identification);
        Client client = clientRepository.findByIdentification(identification);
        if (client != null) {
            log.info("Se encontró el cliente con identificación: {}", identification);
            return clientMapper.toDTO(client);
        } else {
            log.error("No existe el cliente con identificación: {}", identification);
            throw new RuntimeException("No existe el cliente con identificación: " + identification);
        }
    }

    public ClientDTO getClientByEmail(String email) {
        log.info("Buscando cliente con email: {}", email);
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            log.info("Se encontró el cliente con email: {}", email);
            return clientMapper.toDTO(client);
        } else {
            log.error("No existe el cliente con email: {}", email);
            throw new RuntimeException("No existe el cliente con email: " + email);
        }
    }

    public ClientDTO getClientByFullName(String fullName) {
        log.info("Buscando cliente con nombre completo: {}", fullName);
        Client client = clientRepository.findByFullName(fullName);
        if (client != null) {
            log.info("Se encontró el cliente con nombre completo: {}", fullName);
            return clientMapper.toDTO(client);
        } else {
            log.error("No existe el cliente con nombre completo: {}", fullName);
            throw new RuntimeException("No existe el cliente con nombre completo: " + fullName);
        }
    }

    public List<ClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO createClient(ClientDTO createClientDTO) {
        log.info("Creando nuevo cliente desnormalizado.");
        Client client = clientMapper.toModel(createClientDTO);
        client.setUniqueId(uniqueIdGeneration.generateUniqueId());
        client.setCreateDate(LocalDateTime.now());
        client.setLastStateDate(LocalDateTime.now());
        client.setState("ACT");

        // Asignar IDs únicos a phones y addresses si no tienen
        if (client.getPhones() != null) {
            for (Phone phone : client.getPhones()) {
                if (phone.getId() == null) {
                    phone.setId(UUID.randomUUID().toString());
                }
            }
        }

        if (client.getAddresses() != null) {
            for (Address address : client.getAddresses()) {
                if (address.getId() == null) {
                    address.setId(UUID.randomUUID().toString());
                }
            }
        }

        client = clientRepository.save(client);
        return clientMapper.toDTO(client);
    }

    public ClientDTO updateClient(String uniqueId, ClientDTO clientDTO) {
        log.info("Actualizando cliente con uniqueId: {}", uniqueId);
        Client client = clientRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_NOT_FOUND + uniqueId);
                });
        client.setLastName(clientDTO.getLastName());
        client.setFirstName(clientDTO.getFirstName());
        client.setFullName(clientDTO.getFullName());
        client.setEmail(clientDTO.getEmail());
        client.setMaritalState(clientDTO.getMaritalState());
        client.setNationality(clientDTO.getNationality());
        client.setCompanyName(clientDTO.getCompanyName());
        client.setCompanyType(clientDTO.getCompanyType());
        client.setNotes(clientDTO.getNotes());
        client.setIdentificationType(clientDTO.getIdentificationType());
        client.setIdentification(clientDTO.getIdentification());
        client.setMonthlyAverageIncome(clientDTO.getMonthlyAverageIncome());
        return clientMapper.toDTO(clientRepository.save(client));
    }

    public void deleteClient(String uniqueId) {
        log.info("Eliminando cliente con uniqueId: {}", uniqueId);
        Client client = clientRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_NOT_FOUND + uniqueId);
                });
        client.setState("INA");
        clientRepository.save(client);
    }

    public void reactivateClient(String uniqueId) {
        log.info("Reactivando cliente con uniqueId: {}", uniqueId);
        Client client = clientRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error(CLIENT_NOT_FOUND + uniqueId);
                    return new RuntimeException(CLIENT_NOT_FOUND + uniqueId);
                });
        client.setState("ACT");
        clientRepository.save(client);
    }

    public ClientDTO getLastInsertedClient() {
        log.info("Obteniendo el último cliente insertado.");
        return clientMapper.toDTO(clientRepository.findTopByOrderByCreateDateDesc());
    }

    public List<ClientDTO> getClientsByIdentificationType(String identificationType) {
        log.info("Obteniendo clientes por tipo de identificación: {}", identificationType);
        return clientRepository.findByIdentificationType(identificationType).stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ClientDTO> getClientsByCompanyName(String companyName) {
        log.info("Obteniendo clientes por nombre de la empresa: {}", companyName);
        return clientRepository.findByCompanyName(companyName).stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }
}
