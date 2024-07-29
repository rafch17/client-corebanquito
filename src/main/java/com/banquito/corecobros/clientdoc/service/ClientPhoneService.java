package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.repository.ClientPhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientPhoneService {

    private final ClientPhoneRepository repository;
    private static final String CLIENT_PHONE_NOT_FOUND = "No existe el teléfono con id: ";

    public ClientPhoneService(ClientPhoneRepository repository) {
        this.repository = repository;
    }

    public List<Phone> getAllClientPhones() {
        return repository.findAll();
    }

    public Phone getClientPhoneById(String id) {
        Optional<Phone> clientPhoneOpt = repository.findById(id);
        if (clientPhoneOpt.isPresent()) {
            return clientPhoneOpt.get();
        } else {
            throw new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
        }
    }

    public List<Phone> getClientPhonesByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }

    public List<Phone> getDefaultClientPhonesByClientId(String clientId) {
        return repository.findByClientIdAndIsDefault(clientId, true);
    }

    public Phone createClientPhone(Phone clientPhone) {
        return repository.save(clientPhone);
    }

    public Phone updateClientPhone(String id, Phone clientPhoneDetails) {
        Optional<Phone> optionalClientPhone = repository.findById(id);
        if (optionalClientPhone.isPresent()) {
            Phone clientPhone = optionalClientPhone.get();
            clientPhone.setType(clientPhoneDetails.getType());
            clientPhone.setNumber(clientPhoneDetails.getNumber());
            clientPhone.setIsDefault(clientPhoneDetails.getIsDefault());
            return repository.save(clientPhone);
        } else {
            throw new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
        }
    }

    public void deleteClientPhone(String id) {
        Optional<Phone> optionalClientPhone = repository.findById(id);
        if (optionalClientPhone.isPresent()) {
            repository.delete(optionalClientPhone.get());
        } else {
            throw new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
        }
    }
}
