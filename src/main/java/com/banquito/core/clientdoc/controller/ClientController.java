package com.banquito.core.clientdoc.controller;

import com.banquito.core.clientdoc.dto.SimpleClientDTO;
import com.banquito.core.clientdoc.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public SimpleClientDTO getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/identification/{identification}")
    public SimpleClientDTO getClientByIdentification(@PathVariable String identification) {
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    public SimpleClientDTO getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    public SimpleClientDTO getClientByFullName(@PathVariable String fullName) {
        return clientService.getClientByFullName(fullName);
    }

    @GetMapping
    public List<SimpleClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public SimpleClientDTO createClient(@RequestBody SimpleClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public SimpleClientDTO updateClient(@PathVariable String id, @RequestBody SimpleClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
    }

    @PutMapping("/reactivate/{id}")
    public void reactivateClient(@PathVariable String id) {
        clientService.reactivateClient(id);
    }

    @GetMapping("/last")
    public SimpleClientDTO getLastInsertedClient() {
        return clientService.getLastInsertedClient();
    }

    @GetMapping("/identification-type/{identificationType}")
    public List<SimpleClientDTO> getClientsByIdentificationType(@PathVariable String identificationType) {
        return clientService.getClientsByIdentificationType(identificationType);
    }

    @GetMapping("/company-name/{companyName}")
    public List<SimpleClientDTO> getClientsByCompanyName(@PathVariable String companyName) {
        return clientService.getClientsByCompanyName(companyName);
    }
}
