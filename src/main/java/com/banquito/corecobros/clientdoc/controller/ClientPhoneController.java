package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.service.ClientPhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/client-phones")
public class ClientPhoneController {

    private final ClientPhoneService clientPhoneService;

    public ClientPhoneController(ClientPhoneService clientPhoneService) {
        this.clientPhoneService = clientPhoneService;
    }

    @CrossOrigin(origins = "*")

    @GetMapping
    public List<Phone> getAllClientPhones() {
        log.info("Obteniendo todos los teléfonos.");
        return clientPhoneService.getAllClientPhones();
    }

    @GetMapping("/{id}")
    public Phone getClientPhoneById(@PathVariable String id) {
        log.info("Obteniendo teléfono con id: {}", id);
        return clientPhoneService.getClientPhoneById(id);
    }

    @GetMapping("/default/{id}")
    public List<Phone> getDefaultClientPhonesById(@PathVariable String id) {
        log.info("Obteniendo teléfono por defecto con id: {}", id);
        return clientPhoneService.getDefaultClientPhonesById(id);
    }

    @PostMapping
    public Phone createClientPhone(@RequestBody Phone phone) {
        log.info("Creando nuevo teléfono.");
        return clientPhoneService.createClientPhone(phone);
    }

    @PutMapping("/{id}")
    public Phone updateClientPhone(@PathVariable String id, @RequestBody Phone phone) {
        log.info("Actualizando teléfono con id: {}", id);
        return clientPhoneService.updateClientPhone(id, phone);
    }

    @DeleteMapping("/{id}")
    public void deleteClientPhone(@PathVariable String id) {
        log.info("Eliminando teléfono con id: {}", id);
        clientPhoneService.deleteClientPhone(id);
    }
}
