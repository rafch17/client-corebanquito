package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.service.ClientAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/client-addresses")
public class ClientAddressController {

    private final ClientAddressService clientAddressService;

    public ClientAddressController(ClientAddressService clientAddressService) {
        this.clientAddressService = clientAddressService;
    }

    @CrossOrigin(origins = "*")

    @GetMapping
    public List<Address> getAllClientAddresses() {
        log.info("Obteniendo todas las direcciones.");
        return clientAddressService.getAllClientAddresses();
    }

    @GetMapping("/{id}")
    public Address getClientAddressById(@PathVariable String id) {
        log.info("Obteniendo dirección con id: {}", id);
        return clientAddressService.getClientAddressById(id);
    }

    @GetMapping("/default/{id}")
    public List<Address> getDefaultClientAddressesById(@PathVariable String id) {
        log.info("Obteniendo dirección por defecto con id: {}", id);
        return clientAddressService.getDefaultClientAddressesById(id);
    }

    @PostMapping
    public Address createClientAddress(@RequestBody Address address) {
        log.info("Creando nueva dirección.");
        return clientAddressService.createClientAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateClientAddress(@PathVariable String id, @RequestBody Address address) {
        log.info("Actualizando dirección con id: {}", id);
        return clientAddressService.updateClientAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteClientAddress(@PathVariable String id) {
        log.info("Eliminando dirección con id: {}", id);
        clientAddressService.deleteClientAddress(id);
    }
}
