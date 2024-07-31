package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.repository.ClientPhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientPhoneService {

    private final ClientPhoneRepository repository;
    private static final String CLIENT_PHONE_NOT_FOUND = "No existe el teléfono con id: ";

    public ClientPhoneService(ClientPhoneRepository repository) {
        this.repository = repository;
    }

    public List<Phone> getAllClientPhones() {
        log.info("Obteniendo todos los teléfonos.");
        return repository.findAll();
    }

    public Phone getClientPhoneById(String id) {
        log.info("Buscando teléfono con id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_PHONE_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
                });
    }

    public List<Phone> getDefaultClientPhonesById(String id) {
        log.info("Buscando teléfono por defecto con id: {}", id);
        return repository.findByIdAndIsDefault(id, true);
    }

    public Phone createClientPhone(Phone clientPhone) {
        log.info("Creando nuevo teléfono.");
        return repository.save(clientPhone);
    }

    public Phone updateClientPhone(String id, Phone clientPhoneDetails) {
        log.info("Actualizando teléfono con id: {}", id);
        Phone clientPhone = repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_PHONE_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
                });
        clientPhone.setType(clientPhoneDetails.getType());
        clientPhone.setNumber(clientPhoneDetails.getNumber());
        clientPhone.setIsDefault(clientPhoneDetails.getIsDefault());
        clientPhone.setState(clientPhoneDetails.getState());
        return repository.save(clientPhone);
    }

    public void deleteClientPhone(String id) {
        log.info("Eliminando teléfono con id: {}", id);
        Phone clientPhone = repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_PHONE_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_PHONE_NOT_FOUND + id);
                });
        repository.delete(clientPhone);
    }
}
