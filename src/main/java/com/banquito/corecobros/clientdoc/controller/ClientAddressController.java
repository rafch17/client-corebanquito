package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.service.ClientAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/client-address")
public class ClientAddressController {

    private final ClientAddressService clientAddressService;

    public ClientAddressController(ClientAddressService clientAddressService) {
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public List<Address> getAllClientAddresses() {
        log.info("Obteniendo todas las direcciones de clientes.");
        return clientAddressService.getAllClientAddresses();
    }

    @GetMapping("/{id}")
    public Address getClientAddressById(@PathVariable String id) {
        log.info("Buscando dirección con id: {}", id);
        return clientAddressService.getClientAddressById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Address> getClientAddressesByClientId(@PathVariable String clientId) {
        log.info("Obteniendo direcciones del cliente con id: {}", clientId);
        return clientAddressService.getClientAddressesByClientId(clientId);
    }

    @GetMapping("/client/{clientId}/default")
    public List<Address> getDefaultClientAddressesByClientId(@PathVariable String clientId) {
        log.info("Obteniendo direcciones predeterminadas del cliente con id: {}", clientId);
        return clientAddressService.getDefaultClientAddressesByClientId(clientId);
    }

    @PostMapping
    public Address createClientAddress(@RequestBody Address clientAddress) {
        log.info("Creando nueva dirección para el cliente con id: {}", clientAddress.getClientId());
        return clientAddressService.createClientAddress(clientAddress);
    }

    @PutMapping("/{id}")
    public Address updateClientAddress(@PathVariable String id, @RequestBody Address clientAddress) {
        log.info("Actualizando dirección con id: {}", id);
        return clientAddressService.updateClientAddress(id, clientAddress);
    }

    @DeleteMapping("/{id}")
    public void deleteClientAddress(@PathVariable String id) {
        log.info("Eliminando dirección con id: {}", id);
        clientAddressService.deleteClientAddress(id);
    }
}
