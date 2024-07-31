package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.ClientDTO;
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

    @CrossOrigin(origins = "*")

    @GetMapping("/{uniqueId}")
    public ClientDTO getClientByUniqueId(@PathVariable String uniqueId) {
        log.info("Buscando cliente con uniqueId: {}", uniqueId);
        return clientService.getClientByUniqueId(uniqueId);
    }

    @GetMapping("/identification/{identification}")
    public ClientDTO getClientByIdentification(@PathVariable String identification) {
        log.info("Buscando cliente con identificación: {}", identification);
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    public ClientDTO getClientByEmail(@PathVariable String email) {
        log.info("Buscando cliente con email: {}", email);
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    public ClientDTO getClientByFullName(@PathVariable String fullName) {
        log.info("Buscando cliente con nombre completo: {}", fullName);
        return clientService.getClientByFullName(fullName);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientService.getAllClients();
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        log.info("Creando nuevo cliente.");
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{uniqueId}")
    public ClientDTO updateClient(@PathVariable String uniqueId, @RequestBody ClientDTO clientDTO) {
        log.info("Actualizando cliente con uniqueId: {}", uniqueId);
        return clientService.updateClient(uniqueId, clientDTO);
    }

    @DeleteMapping("/{uniqueId}")
    public void deleteClient(@PathVariable String uniqueId) {
        log.info("Eliminando cliente con uniqueId: {}", uniqueId);
        clientService.deleteClient(uniqueId);
    }

    @PutMapping("/reactivate/{uniqueId}")
    public void reactivateClient(@PathVariable String uniqueId) {
        log.info("Reactivando cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClient(uniqueId);
    }

    @GetMapping("/last")
    public ClientDTO getLastInsertedClient() {
        log.info("Obteniendo el último cliente insertado.");
        return clientService.getLastInsertedClient();
    }

    @GetMapping("/identification-type/{identificationType}")
    public List<ClientDTO> getClientsByIdentificationType(@PathVariable String identificationType) {
        log.info("Obteniendo clientes por tipo de identificación: {}", identificationType);
        return clientService.getClientsByIdentificationType(identificationType);
    }

    @GetMapping("/company-name/{companyName}")
    public List<ClientDTO> getClientsByCompanyName(@PathVariable String companyName) {
        log.info("Obteniendo clientes por nombre de la empresa: {}", companyName);
        return clientService.getClientsByCompanyName(companyName);
    }
}
