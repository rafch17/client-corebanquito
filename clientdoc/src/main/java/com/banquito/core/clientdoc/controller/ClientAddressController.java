package com.banquito.core.clientdoc.controller;

import com.banquito.core.clientdoc.model.Address;
import com.banquito.core.clientdoc.service.ClientAddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client-address")
public class ClientAddressController {

    private final ClientAddressService clientAddressService;

    public ClientAddressController(ClientAddressService clientAddressService) {
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public List<Address> getAllClientAddresses() {
        return clientAddressService.getAllClientAddresses();
    }

    @GetMapping("/{id}")
    public Address getClientAddressById(@PathVariable String id) {
        return clientAddressService.getClientAddressById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Address> getClientAddressesByClientId(@PathVariable String clientId) {
        return clientAddressService.getClientAddressesByClientId(clientId);
    }

    @GetMapping("/default/client/{clientId}")
    public List<Address> getDefaultClientAddressesByClientId(@PathVariable String clientId) {
        return clientAddressService.getDefaultClientAddressesByClientId(clientId);
    }

    @PostMapping
    public Address createClientAddress(@RequestBody Address address) {
        return clientAddressService.createClientAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateClientAddress(@PathVariable String id, @RequestBody Address address) {
        return clientAddressService.updateClientAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteClientAddress(@PathVariable String id) {
        clientAddressService.deleteClientAddress(id);
    }
}
