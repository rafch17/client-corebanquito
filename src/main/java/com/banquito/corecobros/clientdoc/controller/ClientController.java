package com.banquito.corecobros.clientdoc.controller;

import com.banquito.corecobros.clientdoc.dto.ClientDTO;
import com.banquito.corecobros.clientdoc.model.Address;
import com.banquito.corecobros.clientdoc.model.Phone;
import com.banquito.corecobros.clientdoc.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-microservice/api/v1/clients")
@CrossOrigin(origins = "*")
@Tag(name = "Client", description = "Endpoints for managing clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Create a new client", description = "Create a new client along with their phones and addresses")
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        log.info("Creando nuevo cliente.");
        return clientService.createClient(clientDTO);
    }

    @GetMapping
    @Operation(summary = "Get all clients", description = "Retrieve a list of all clients")
    public List<ClientDTO> getAllClients() {
        log.info("Obteniendo todos los clientes.");
        return clientService.getAllClients();
    }

    @GetMapping("/{uniqueId}")
    @Operation(summary = "Get client by uniqueId", description = "Retrieve a client by their uniqueId")
    public ClientDTO getClientByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo cliente por uniqueId: {}", uniqueId);
        return clientService.getClientByUniqueId(uniqueId);
    }

    @GetMapping("/identification/{identification}")
    @Operation(summary = "Get client by identification", description = "Retrieve a client by their identification")
    public ClientDTO getClientByIdentification(@PathVariable String identification) {
        log.info("Obteniendo cliente por identificación: {}", identification);
        return clientService.getClientByIdentification(identification);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get client by email", description = "Retrieve a client by their email")
    public ClientDTO getClientByEmail(@PathVariable String email) {
        log.info("Obteniendo cliente por email: {}", email);
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/fullname/{fullName}")
    @Operation(summary = "Get client by full name", description = "Retrieve a client by their full name")
    public ClientDTO getClientByFullName(@PathVariable String fullName) {
        log.info("Obteniendo cliente por nombre completo: {}", fullName);
        return clientService.getClientByFullName(fullName);
    }

    @GetMapping("/last")
    @Operation(summary = "Get the last inserted client", description = "Retrieve the last inserted client")
    public ClientDTO getLastInsertedClient() {
        log.info("Obteniendo el último cliente insertado.");
        return clientService.getLastInsertedClient();
    }

    @GetMapping("/identification-type/{identificationType}")
    @Operation(summary = "Get clients by identification type", description = "Retrieve clients by their identification type")
    public List<ClientDTO> getClientsByIdentificationType(@PathVariable String identificationType) {
        log.info("Obteniendo clientes por tipo de identificación: {}", identificationType);
        return clientService.getClientsByIdentificationType(identificationType);
    }

    @GetMapping("/company-name/{companyName}")
    @Operation(summary = "Get clients by company name", description = "Retrieve clients by their company name")
    public List<ClientDTO> getClientsByCompanyName(@PathVariable String companyName) {
        log.info("Obteniendo clientes por nombre de la empresa: {}", companyName);
        return clientService.getClientsByCompanyName(companyName);
    }

    @PutMapping("/{uniqueId}")
    @Operation(summary = "Update a client", description = "Update an existing client by their uniqueId")
    public ClientDTO updateClient(@PathVariable String uniqueId, @RequestBody ClientDTO clientDTO) {
        log.info("Actualizando cliente con uniqueId: {}", uniqueId);
        return clientService.updateClient(uniqueId, clientDTO);
    }

    @DeleteMapping("/{uniqueId}")
    @Operation(summary = "Delete a client", description = "Logically delete a client by their uniqueId")
    public void deleteClient(@PathVariable String uniqueId) {
        log.info("Eliminando cliente con uniqueId: {}", uniqueId);
        clientService.deleteClient(uniqueId);
    }

    @PutMapping("/reactivate/{uniqueId}")
    @Operation(summary = "Reactivate a client", description = "Reactivate a logically deleted client by their uniqueId")
    public void reactivateClient(@PathVariable String uniqueId) {
        log.info("Reactivando cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClient(uniqueId);
    }

    @GetMapping("/{uniqueId}/phones")
    @Operation(summary = "Get client phones by uniqueId", description = "Retrieve all phones of a client by their uniqueId")
    public List<Phone> getClientPhonesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo teléfonos del cliente con uniqueId: {}", uniqueId);
        return clientService.getClientPhonesByUniqueId(uniqueId);
    }

    @GetMapping("/{uniqueId}/phones/default")
    @Operation(summary = "Get default client phones by uniqueId", description = "Retrieve all default phones of a client by their uniqueId")
    public List<Phone> getDefaultClientPhonesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo teléfonos predeterminados del cliente con uniqueId: {}", uniqueId);
        return clientService.getDefaultClientPhonesByUniqueId(uniqueId);
    }

    @PutMapping("/{uniqueId}/phones")
    @Operation(summary = "Update a client phone", description = "Update an existing phone of a client by their uniqueId")
    public Phone updateClientPhone(@PathVariable String uniqueId, @RequestBody Phone phoneDetails) {
        log.info("Actualizando teléfono del cliente con uniqueId: {}", uniqueId);
        return clientService.updateClientPhone(uniqueId, phoneDetails);
    }

    @DeleteMapping("/{uniqueId}/phones/{phoneId}")
    @Operation(summary = "Delete a client phone", description = "Logically delete a phone of a client by their uniqueId and phoneId")
    public void deleteClientPhone(@PathVariable String uniqueId, @PathVariable String phoneId) {
        log.info("Eliminando teléfono del cliente con uniqueId: {}", uniqueId);
        clientService.deleteClientPhone(uniqueId, phoneId);
    }

    @PutMapping("/{uniqueId}/phones/reactivate/{phoneId}")
    @Operation(summary = "Reactivate a client phone", description = "Reactivate a logically deleted phone of a client by their uniqueId and phoneId")
    public void reactivateClientPhone(@PathVariable String uniqueId, @PathVariable String phoneId) {
        log.info("Reactivando teléfono del cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClientPhone(uniqueId, phoneId);
    }

    @GetMapping("/{uniqueId}/addresses")
    @Operation(summary = "Get client addresses by uniqueId", description = "Retrieve all addresses of a client by their uniqueId")
    public List<Address> getClientAddressesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo direcciones del cliente con uniqueId: {}", uniqueId);
        return clientService.getClientAddressesByUniqueId(uniqueId);
    }

    @GetMapping("/{uniqueId}/addresses/default")
    @Operation(summary = "Get default client addresses by uniqueId", description = "Retrieve all default addresses of a client by their uniqueId")
    public List<Address> getDefaultClientAddressesByUniqueId(@PathVariable String uniqueId) {
        log.info("Obteniendo direcciones predeterminadas del cliente con uniqueId: {}", uniqueId);
        return clientService.getDefaultClientAddressesByUniqueId(uniqueId);
    }

    @PutMapping("/{uniqueId}/addresses")
    @Operation(summary = "Update a client address", description = "Update an existing address of a client by their uniqueId")
    public Address updateClientAddress(@PathVariable String uniqueId, @RequestBody Address addressDetails) {
        log.info("Actualizando dirección del cliente con uniqueId: {}", uniqueId);
        return clientService.updateClientAddress(uniqueId, addressDetails);
    }

    @DeleteMapping("/{uniqueId}/addresses/{addressId}")
    @Operation(summary = "Delete a client address", description = "Logically delete an address of a client by their uniqueId and addressId")
    public void deleteClientAddress(@PathVariable String uniqueId, @PathVariable String addressId) {
        log.info("Eliminando dirección del cliente con uniqueId: {}", uniqueId);
        clientService.deleteClientAddress(uniqueId, addressId);
    }

    @PutMapping("/{uniqueId}/addresses/reactivate/{addressId}")
    @Operation(summary = "Reactivate a client address", description = "Reactivate a logically deleted address of a client by their uniqueId and addressId")
    public void reactivateClientAddress(@PathVariable String uniqueId, @PathVariable String addressId) {
        log.info("Reactivando dirección del cliente con uniqueId: {}", uniqueId);
        clientService.reactivateClientAddress(uniqueId, addressId);
    }
}
