package com.banquito.corecobros.clientdoc.service;

import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.repository.ClientAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientAddressService {

    private final ClientAddressRepository repository;
    private static final String CLIENT_ADDRESS_NOT_FOUND = "No existe la dirección con id: ";

    public ClientAddressService(ClientAddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAllClientAddresses() {
        log.info("Obteniendo todas las direcciones.");
        return repository.findAll();
    }

    public Address getClientAddressById(String id) {
        log.info("Buscando dirección con id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_ADDRESS_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
                });
    }

    public List<Address> getDefaultClientAddressesById(String id) {
        log.info("Buscando dirección por defecto con id: {}", id);
        return repository.findByIdAndIsDefault(id, true);
    }

    public Address createClientAddress(Address clientAddress) {
        log.info("Creando nueva dirección.");
        return repository.save(clientAddress);
    }

    public Address updateClientAddress(String id, Address clientAddressDetails) {
        log.info("Actualizando dirección con id: {}", id);
        Address clientAddress = repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_ADDRESS_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
                });
        clientAddress.setType(clientAddressDetails.getType());
        clientAddress.setLine1(clientAddressDetails.getLine1());
        clientAddress.setLine2(clientAddressDetails.getLine2());
        clientAddress.setLatitude(clientAddressDetails.getLatitude());
        clientAddress.setLongitude(clientAddressDetails.getLongitude());
        clientAddress.setIsDefault(clientAddressDetails.getIsDefault());
        clientAddress.setState(clientAddressDetails.getState());
        return repository.save(clientAddress);
    }

    public void deleteClientAddress(String id) {
        log.info("Eliminando dirección con id: {}", id);
        Address clientAddress = repository.findById(id)
                .orElseThrow(() -> {
                    log.error(CLIENT_ADDRESS_NOT_FOUND + id);
                    return new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
                });
        repository.delete(clientAddress);
    }
}
