package com.banquito.core.clientdoc.controller;

import com.banquito.core.clientdoc.model.Phone;
import com.banquito.core.clientdoc.service.ClientPhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client-phone")
public class ClientPhoneController {

    private final ClientPhoneService clientPhoneService;

    public ClientPhoneController(ClientPhoneService clientPhoneService) {
        this.clientPhoneService = clientPhoneService;
    }

    @GetMapping
    public List<Phone> getAllClientPhones() {
        return clientPhoneService.getAllClientPhones();
    }

    @GetMapping("/{id}")
    public Phone getClientPhoneById(@PathVariable String id) {
        return clientPhoneService.getClientPhoneById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Phone> getClientPhonesByClientId(@PathVariable String clientId) {
        return clientPhoneService.getClientPhonesByClientId(clientId);
    }

    @GetMapping("/default/client/{clientId}")
    public List<Phone> getDefaultClientPhonesByClientId(@PathVariable String clientId) {
        return clientPhoneService.getDefaultClientPhonesByClientId(clientId);
    }

    @PostMapping
    public Phone createClientPhone(@RequestBody Phone phone) {
        return clientPhoneService.createClientPhone(phone);
    }

    @PutMapping("/{id}")
    public Phone updateClientPhone(@PathVariable String id, @RequestBody Phone phone) {
        return clientPhoneService.updateClientPhone(id, phone);
    }

    @DeleteMapping("/{id}")
    public void deleteClientPhone(@PathVariable String id) {
        clientPhoneService.deleteClientPhone(id);
    }
}
