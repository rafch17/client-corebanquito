package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.SimpleClientDTO;
import com.banquito.corecobros.clientdoc.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public SimpleClientDTO getClientById(@PathVariable String id) {
        log.info("Buscando cliente con id: {}", id);
        return clientService.getClientById(id);
    }

    @GetMapping("/identification/{identification}")
    public SimpleClientDTO getClientByIdentification(@PathVariable String identification) {
        log.info("Buscando cliente con identificación: {}", identification);
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    public SimpleClientDTO getClientByEmail(@PathVariable String email) {
        log.info("Buscando cliente con email: {}", email);
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    public SimpleClientDTO getClientByFullName(@PathVariable String fullName) {
        log.info("Buscando cliente con nombre completo: {}", fullName);
        return clientService.getClientByFullName(fullName);
    }

    @GetMapping
    public List<SimpleClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientService.getAllClients();
    }

    @PostMapping
    public SimpleClientDTO createClient(@RequestBody SimpleClientDTO clientDTO) {
        log.info("Creando nuevo cliente.");
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public SimpleClientDTO updateClient(@PathVariable String id, @RequestBody SimpleClientDTO clientDTO) {
        log.info("Actualizando cliente con id: {}", id);
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        log.info("Eliminando cliente con id: {}", id);
        clientService.deleteClient(id);
    }

    @PutMapping("/reactivate/{id}")
    public void reactivateClient(@PathVariable String id) {
        log.info("Reactivando cliente con id: {}", id);
        clientService.reactivateClient(id);
    }

    @GetMapping("/last")
    public SimpleClientDTO getLastInsertedClient() {
        log.info("Obteniendo el último cliente insertado.");
        return clientService.getLastInsertedClient();
    }

    @GetMapping("/identification-type/{identificationType}")
    public List<SimpleClientDTO> getClientsByIdentificationType(@PathVariable String identificationType) {
        log.info("Obteniendo clientes por tipo de identificación: {}", identificationType);
        return clientService.getClientsByIdentificationType(identificationType);
    }

    @GetMapping("/company-name/{companyName}")
    public List<SimpleClientDTO> getClientsByCompanyName(@PathVariable String companyName) {
        log.info("Obteniendo clientes por nombre de la empresa: {}", companyName);
        return clientService.getClientsByCompanyName(companyName);
    }
}
