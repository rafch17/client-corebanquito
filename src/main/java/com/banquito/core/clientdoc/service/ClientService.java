package com.banquito.core.clientdoc.service;

import com.banquito.core.clientdoc.model.Client;
import com.banquito.core.clientdoc.repository.ClientRepository;
import com.banquito.core.clientdoc.dto.SimpleClientDTO;
import com.banquito.core.clientdoc.util.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private static final String CLIENT_NOT_FOUND = "No existe el cliente con id: ";

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public SimpleClientDTO getClientById(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CLIENT_NOT_FOUND + id));
        return clientMapper.toSimpleClientDTO(client);
    }

    public SimpleClientDTO getClientByIdentification(String identification) {
        return clientMapper.toSimpleClientDTO(clientRepository.findByIdentification(identification));
    }

    public SimpleClientDTO getClientByEmail(String email) {
        return clientMapper.toSimpleClientDTO(clientRepository.findByEmail(email));
    }

    public SimpleClientDTO getClientByFullName(String fullName) {
        return clientMapper.toSimpleClientDTO(clientRepository.findByFullName(fullName));
    }

    public List<SimpleClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toSimpleClientDTO)
                .collect(Collectors.toList());
    }

    public SimpleClientDTO createClient(SimpleClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        return clientMapper.toSimpleClientDTO(clientRepository.save(client));
    }

    public SimpleClientDTO updateClient(String id, SimpleClientDTO clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CLIENT_NOT_FOUND + id));
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

        return clientMapper.toSimpleClientDTO(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CLIENT_NOT_FOUND + id));
        client.setState("INA");
        clientRepository.save(client);
    }

    @Transactional
    public void reactivateClient(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CLIENT_NOT_FOUND + id));
        client.setState("ACT");
        clientRepository.save(client);
    }

    public SimpleClientDTO getLastInsertedClient() {
        return clientMapper.toSimpleClientDTO(clientRepository.findTopByOrderByCreateDateDesc());
    }

    public List<SimpleClientDTO> getClientsByIdentificationType(String identificationType) {
        return clientRepository.findByIdentificationType(identificationType).stream()
                .map(clientMapper::toSimpleClientDTO)
                .collect(Collectors.toList());
    }

    public List<SimpleClientDTO> getClientsByCompanyName(String companyName) {
        return clientRepository.findByCompanyName(companyName).stream()
                .map(clientMapper::toSimpleClientDTO)
                .collect(Collectors.toList());
    }
}
