package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.ClientDTO;
import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Clientes

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        log.info("Creando nuevo cliente.");
        return clientService.createClient(clientDTO);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientService.getAllClients();
    }

    @GetMapping("/{uniqueId}")
    public ClientDTO getClientByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo cliente por uniqueId: {}", uniqueId);
        return clientService.getClientByUniqueId(uniqueId);
    }

    @GetMapping("/identification/{identification}")
    public ClientDTO getClientByIdentification(@PathVariable String identification) {
        log.info("Obteniendo cliente por identificación: {}", identification);
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    public ClientDTO getClientByEmail(@PathVariable String email) {
        log.info("Obteniendo cliente por email: {}", email);
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    public ClientDTO getClientByFullName(@PathVariable String fullName) {
        log.info("Obteniendo cliente por nombre completo: {}", fullName);
        return clientService.getClientByFullName(fullName);
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

    // Teléfonos

    @GetMapping("/{uniqueId}/phones")
    public List<Phone> getClientPhonesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo teléfonos del cliente con uniqueId: {}", uniqueId);
        return clientService.getClientPhonesByUniqueId(uniqueId);
    }

    @GetMapping("/{uniqueId}/phones/default")
    public List<Phone> getDefaultClientPhonesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo teléfonos predeterminados del cliente con uniqueId: {}", uniqueId);
        return clientService.getDefaultClientPhonesByUniqueId(uniqueId);
    }

    @PutMapping("/{uniqueId}/phones")
    public Phone updateClientPhone(@PathVariable String uniqueId, @RequestBody Phone phoneDetails) {
        log.info("Actualizando teléfono del cliente con uniqueId: {}", uniqueId);
        return clientService.updateClientPhone(uniqueId, phoneDetails);
    }

    @DeleteMapping("/{uniqueId}/phones/{phoneId}")
    public void deleteClientPhone(@PathVariable String uniqueId, @PathVariable String phoneId) {
        log.info("Eliminando teléfono del cliente con uniqueId: {}", uniqueId);
        clientService.deleteClientPhone(uniqueId, phoneId);
    }

    @PutMapping("/{uniqueId}/phones/reactivate/{phoneId}")
    public void reactivateClientPhone(@PathVariable String uniqueId, @PathVariable String phoneId) {
        log.info("Reactivando teléfono del cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClientPhone(uniqueId, phoneId);
    }

    // Direcciones

    @GetMapping("/{uniqueId}/addresses")
    public List<Address> getClientAddressesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo direcciones del cliente con uniqueId: {}", uniqueId);
        return clientService.getClientAddressesByUniqueId(uniqueId);
    }

    @GetMapping("/{uniqueId}/addresses/default")
    public List<Address> getDefaultClientAddressesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo direcciones predeterminadas del cliente con uniqueId: {}", uniqueId);
        return clientService.getDefaultClientAddressesByUniqueId(uniqueId);
    }

    @PutMapping("/{uniqueId}/addresses")
    public Address updateClientAddress(@PathVariable String uniqueId, @RequestBody Address addressDetails) {
        log.info("Actualizando dirección del cliente con uniqueId: {}", uniqueId);
        return clientService.updateClientAddress(uniqueId, addressDetails);
    }

    @DeleteMapping("/{uniqueId}/addresses/{addressId}")
    public void deleteClientAddress(@PathVariable String uniqueId, @PathVariable String addressId) {
        log.info("Eliminando dirección del cliente con uniqueId: {}", uniqueId);
        clientService.deleteClientAddress(uniqueId, addressId);
    }

    @PutMapping("/{uniqueId}/addresses/reactivate/{addressId}")
    public void reactivateClientAddress(@PathVariable String uniqueId, @PathVariable String addressId) {
        log.info("Reactivando dirección del cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClientAddress(uniqueId, addressId);
    }
}
