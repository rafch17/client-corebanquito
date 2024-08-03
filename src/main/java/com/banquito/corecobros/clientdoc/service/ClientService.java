package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.dto.ClientDTO;
import com.banquito.corecobros.clientdoc.model.Client;
import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.repository.ClientRepository;
import com.banquito.corecobros.clientdoc.util.mapper.ClientMapper;
import com.banquito.corecobros.clientdoc.util.UniqueId.UniqueIdGeneration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UniqueIdGeneration uniqueIdGeneration;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper,
            UniqueIdGeneration uniqueIdGeneration) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        log.info("Creando nuevo cliente desnormalizado.");
        Client client = clientMapper.toModel(clientDTO);
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

    public List<ClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Métodos para manejar los teléfonos
    public List<Phone> getClientPhonesByUniqueId(String uniqueId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        return client.getPhones();
    }

    public List<Phone> getDefaultClientPhonesByUniqueId(String uniqueId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        return client.getPhones().stream()
                .filter(Phone::getIsDefault)
                .collect(Collectors.toList());
    }

    public Phone updateClientPhone(String uniqueId, Phone phoneDetails) {
        Client client = getClientEntityByUniqueId(uniqueId);
        Phone phone = client.getPhones().stream()
                .filter(p -> p.getId().equals(phoneDetails.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe el teléfono con id: " + phoneDetails.getId()));

        phone.setType(phoneDetails.getType());
        phone.setNumber(phoneDetails.getNumber());
        phone.setIsDefault(phoneDetails.getIsDefault());
        phone.setState(phoneDetails.getState());
        clientRepository.save(client);
        return phone;
    }

    public void deleteClientPhone(String uniqueId, String phoneId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        client.getPhones().stream()
                .filter(p -> p.getId().equals(phoneId))
                .findFirst()
                .ifPresent(phone -> phone.setState("INA"));
        clientRepository.save(client);
    }

    public void reactivateClientPhone(String uniqueId, String phoneId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        client.getPhones().stream()
                .filter(p -> p.getId().equals(phoneId))
                .findFirst()
                .ifPresent(phone -> phone.setState("ACT"));
        clientRepository.save(client);
    }

    // Métodos para manejar las direcciones
    public List<Address> getClientAddressesByUniqueId(String uniqueId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        return client.getAddresses();
    }

    public List<Address> getDefaultClientAddressesByUniqueId(String uniqueId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        return client.getAddresses().stream()
                .filter(Address::getIsDefault)
                .collect(Collectors.toList());
    }

    public Address updateClientAddress(String uniqueId, Address addressDetails) {
        Client client = getClientEntityByUniqueId(uniqueId);
        Address address = client.getAddresses().stream()
                .filter(a -> a.getId().equals(addressDetails.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe la dirección con id: " + addressDetails.getId()));

        address.setType(addressDetails.getType());
        address.setLine1(addressDetails.getLine1());
        address.setLine2(addressDetails.getLine2());
        address.setLatitude(addressDetails.getLatitude());
        address.setLongitude(addressDetails.getLongitude());
        address.setIsDefault(addressDetails.getIsDefault());
        address.setState(addressDetails.getState());
        clientRepository.save(client);
        return address;
    }

    public void deleteClientAddress(String uniqueId, String addressId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        client.getAddresses().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .ifPresent(address -> address.setState("INA"));
        clientRepository.save(client);
    }

    public void reactivateClientAddress(String uniqueId, String addressId) {
        Client client = getClientEntityByUniqueId(uniqueId);
        client.getAddresses().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .ifPresent(address -> address.setState("ACT"));
        clientRepository.save(client);
    }

    public ClientDTO getClientByUniqueId(String uniqueId) {
        log.info("Buscando cliente con uniqueId: {}", uniqueId);
        Client client = getClientEntityByUniqueId(uniqueId);
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

    public ClientDTO updateClient(String uniqueId, ClientDTO clientDTO) {
        log.info("Actualizando cliente con uniqueId: {}", uniqueId);
        Client client = getClientEntityByUniqueId(uniqueId);
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
        Client client = getClientEntityByUniqueId(uniqueId);
        client.setState("INA");
        clientRepository.save(client);
    }

    public void reactivateClient(String uniqueId) {
        log.info("Reactivando cliente con uniqueId: {}", uniqueId);
        Client client = getClientEntityByUniqueId(uniqueId);
        client.setState("ACT");
        clientRepository.save(client);
    }

    private Client getClientEntityByUniqueId(String uniqueId) {
        Client client = clientRepository.findByUniqueId(uniqueId);
        if (client == null) {
            throw new RuntimeException("No existe el cliente con uniqueId: " + uniqueId);
        }
        return client;
    }
}
