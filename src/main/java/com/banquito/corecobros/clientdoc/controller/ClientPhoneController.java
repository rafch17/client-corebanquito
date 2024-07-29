package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.service.ClientPhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/client-phone")
public class ClientPhoneController {

    private final ClientPhoneService clientPhoneService;

    public ClientPhoneController(ClientPhoneService clientPhoneService) {
        this.clientPhoneService = clientPhoneService;
    }

    @GetMapping
    public List<Phone> getAllClientPhones() {
        log.info("Obteniendo todos los teléfonos de clientes.");
        return clientPhoneService.getAllClientPhones();
    }

    @GetMapping("/{id}")
    public Phone getClientPhoneById(@PathVariable String id) {
        log.info("Buscando teléfono con id: {}", id);
        return clientPhoneService.getClientPhoneById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Phone> getClientPhonesByClientId(@PathVariable String clientId) {
        log.info("Obteniendo teléfonos del cliente con id: {}", clientId);
        return clientPhoneService.getClientPhonesByClientId(clientId);
    }

    @GetMapping("/client/{clientId}/default")
    public List<Phone> getDefaultClientPhonesByClientId(@PathVariable String clientId) {
        log.info("Obteniendo teléfonos predeterminados del cliente con id: {}", clientId);
        return clientPhoneService.getDefaultClientPhonesByClientId(clientId);
    }

    @PostMapping
    public Phone createClientPhone(@RequestBody Phone clientPhone) {
        log.info("Creando nuevo teléfono para el cliente con id: {}", clientPhone.getClientId());
        return clientPhoneService.createClientPhone(clientPhone);
    }

    @PutMapping("/{id}")
    public Phone updateClientPhone(@PathVariable String id, @RequestBody Phone clientPhone) {
        log.info("Actualizando teléfono con id: {}", id);
        return clientPhoneService.updateClientPhone(id, clientPhone);
    }

    @DeleteMapping("/{id}")
    public void deleteClientPhone(@PathVariable String id) {
        log.info("Eliminando teléfono con id: {}", id);
        clientPhoneService.deleteClientPhone(id);
    }
}
