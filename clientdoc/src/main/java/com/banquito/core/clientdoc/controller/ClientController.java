package com.banquito.core.clientdoc.controller;

import com.banquito.core.clientdoc.dto.ClientDTO;
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
    public ClientDTO getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/identification/{identification}")
    public ClientDTO getClientByIdentification(@PathVariable String identification) {
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    public ClientDTO getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    public ClientDTO getClientByFullName(@PathVariable String fullName) {
        return clientService.getClientByFullName(fullName);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
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
    public ClientDTO getLastInsertedClient() {
        return clientService.getLastInsertedClient();
    }

    @GetMapping("/identification-type/{identificationType}")
    public List<ClientDTO> getClientsByIdentificationType(@PathVariable String identificationType) {
        return clientService.getClientsByIdentificationType(identificationType);
    }

    @GetMapping("/company-name/{companyName}")
    public List<ClientDTO> getClientsByCompanyName(@PathVariable String companyName) {
        return clientService.getClientsByCompanyName(companyName);
    }
}
