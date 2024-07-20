package com.banquito.core.clientdoc.service;

import com.banquito.core.clientdoc.model.Address;
import com.banquito.core.clientdoc.repository.ClientAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientAddressService {

    private final ClientAddressRepository repository;
    private static final String CLIENT_ADDRESS_NOT_FOUND = "No existe la dirección con id: ";

    public ClientAddressService(ClientAddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAllClientAddresses() {
        return repository.findAll();
    }

    public Address getClientAddressById(String id) {
        Optional<Address> clientAddressOpt = repository.findById(id);
        if (clientAddressOpt.isPresent()) {
            return clientAddressOpt.get();
        } else {
            throw new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
        }
    }

    public List<Address> getClientAddressesByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }

    public List<Address> getDefaultClientAddressesByClientId(String clientId) {
        return repository.findByClientIdAndIsDefault(clientId, true);
    }

    public Address createClientAddress(Address clientAddress) {
        return repository.save(clientAddress);
    }

    public Address updateClientAddress(String id, Address clientAddressDetails) {
        Optional<Address> optionalClientAddress = repository.findById(id);
        if (optionalClientAddress.isPresent()) {
            Address clientAddress = optionalClientAddress.get();
            clientAddress.setType(clientAddressDetails.getType());
            clientAddress.setLine1(clientAddressDetails.getLine1());
            clientAddress.setLine2(clientAddressDetails.getLine2());
            clientAddress.setLatitude(clientAddressDetails.getLatitude());
            clientAddress.setLongitude(clientAddressDetails.getLongitude());
            clientAddress.setIsDefault(clientAddressDetails.getIsDefault());
            return repository.save(clientAddress);
        } else {
            throw new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
        }
    }

    public void deleteClientAddress(String id) {
        Optional<Address> optionalClientAddress = repository.findById(id);
        if (optionalClientAddress.isPresent()) {
            repository.delete(optionalClientAddress.get());
        } else {
            throw new RuntimeException(CLIENT_ADDRESS_NOT_FOUND + id);
        }
    }
}
