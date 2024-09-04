package com.banquito.core.clientdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banquito.core.clientdoc.dto.AddressDTO;
import com.banquito.core.clientdoc.dto.ClientDTO;
import com.banquito.core.clientdoc.dto.PhoneDTO;
import com.banquito.core.clientdoc.model.Address;
import com.banquito.core.clientdoc.model.Client;
import com.banquito.core.clientdoc.model.Phone;
import com.banquito.core.clientdoc.repository.ClientRepository;
import com.banquito.core.clientdoc.util.UniqueId.UniqueIdGeneration;
import com.banquito.core.clientdoc.util.mapper.ClientMapper;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private UniqueIdGeneration uniqueIdGeneration;

    @InjectMocks
    private ClientService clientService;

    private ClientDTO clientDTO;
    private Client client;

    @BeforeEach
    void setUp() {
        clientDTO = ClientDTO.builder()
        .id(UUID.randomUUID().toString())
        .codeSegment("SEG1")
        .nameSegment("Micro")
        .uniqueId("uniqueId123")
        .clientType("Individual")
        .identificationType("ID")
        .identification("1234567890")
        .firstName("John")
        .lastName("Doe")
        .email("john.doe@example.com")
        .birthDate(LocalDate.of(1990, 1, 1))
        .companyName("ESPE")
        .companyType("Private")
        .state("ACT")
        .createDate(LocalDateTime.now())
        .lastStateDate(LocalDateTime.now())
        .nationality("Ecuadorian")
        .maritalState("Single")
        .monthlyAverageIncome(BigDecimal.valueOf(5000))
        .notes("Prueba")
        .phones(Collections.singletonList(new PhoneDTO(UUID.randomUUID().toString(), "Mobile", "123456789", true, "ACT")))
        .addresses(Collections.singletonList(new AddressDTO(UUID.randomUUID().toString(), "Home", "123 Main St", "Apt 1", 12.34567f, 76.54321f, true, "ACT")))
        .build();

        client = new Client();
        client.setId(clientDTO.getId());
        client.setCodeSegment(clientDTO.getCodeSegment());
        client.setNameSegment(clientDTO.getNameSegment());
        client.setUniqueId(clientDTO.getUniqueId());
        client.setClientType(clientDTO.getClientType());
        client.setIdentificationType(clientDTO.getIdentificationType());
        client.setIdentification(clientDTO.getIdentification());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setCompanyName(clientDTO.getCompanyName());
        client.setCompanyType(clientDTO.getCompanyType());
        client.setState(clientDTO.getState());
        client.setCreateDate(clientDTO.getCreateDate());
        client.setLastStateDate(clientDTO.getLastStateDate());
        client.setNationality(clientDTO.getNationality());
        client.setMaritalState(clientDTO.getMaritalState());
        client.setMonthlyAverageIncome(clientDTO.getMonthlyAverageIncome());
        client.setNotes(clientDTO.getNotes());
        client.setPhones(Arrays.asList(new Phone()));
        client.setAddresses(Arrays.asList(new Address()));

        Phone phone = new Phone();
        phone.setId(clientDTO.getPhones().get(0).getId());
        phone.setType(clientDTO.getPhones().get(0).getType());
        phone.setNumber(clientDTO.getPhones().get(0).getNumber());
        phone.setIsDefault(clientDTO.getPhones().get(0).getIsDefault());
        phone.setState(clientDTO.getPhones().get(0).getState());
    
        Address address = new Address();
        address.setId(clientDTO.getAddresses().get(0).getId());
        address.setType(clientDTO.getAddresses().get(0).getType());
        address.setLine1(clientDTO.getAddresses().get(0).getLine1());
        address.setLine2(clientDTO.getAddresses().get(0).getLine2());
        address.setLatitude(clientDTO.getAddresses().get(0).getLatitude());
        address.setLongitude(clientDTO.getAddresses().get(0).getLongitude());
        address.setIsDefault(clientDTO.getAddresses().get(0).getIsDefault());
        address.setState(clientDTO.getAddresses().get(0).getState());

        client.setPhones(Collections.singletonList(phone));
        client.setAddresses(Collections.singletonList(address));
    }

    @Test
    void testCreateClient() {
        when(clientMapper.toModel(any(ClientDTO.class))).thenReturn(client);
        when(uniqueIdGeneration.generateUniqueId()).thenReturn("uniqueId123");
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO createdClient = clientService.createClient(clientDTO);

        assertNotNull(createdClient);
        assertEquals("uniqueId123", createdClient.getUniqueId());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        List<ClientDTO> clients = clientService.getAllClients();

        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClientByUniqueId() {
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO foundClient = clientService.getClientByUniqueId("uniqueId123");

        assertNotNull(foundClient);
        assertEquals("uniqueId123", foundClient.getUniqueId());
        verify(clientRepository, times(1)).findByUniqueId("uniqueId123");
    }

    @Test
    void testUpdateClient() {
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO updatedClient = clientService.updateClient("uniqueId123", clientDTO);

        assertNotNull(updatedClient);
        assertEquals("uniqueId123", updatedClient.getUniqueId());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);

        clientService.deleteClient("uniqueId123");

        assertEquals("INA", client.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testReactivateClient() {
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);

        clientService.reactivateClient("uniqueId123");

        assertEquals("ACT", client.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientByEmail() {
        when(clientRepository.findByEmail(anyString())).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO foundClient = clientService.getClientByEmail("john.doe@example.com");

        assertNotNull(foundClient);
        assertEquals("john.doe@example.com", foundClient.getEmail());
        verify(clientRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testGetClientByIdentification() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO foundClient = clientService.getClientByIdentification("1234567890");

        assertNotNull(foundClient);
        assertEquals("1234567890", foundClient.getIdentification());
        verify(clientRepository, times(1)).findByIdentification("1234567890");
    }

    @Test
    void testGetLastInsertedClient() {
        when(clientRepository.findTopByOrderByCreateDateDesc()).thenReturn(client);
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        ClientDTO lastInsertedClient = clientService.getLastInsertedClient();

        assertNotNull(lastInsertedClient);
        verify(clientRepository, times(1)).findTopByOrderByCreateDateDesc();
    }

    @Test
    void testGetClientPhonesByUniqueId() {
        List<Phone> phones = Arrays.asList(new Phone(), new Phone());
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        client.setPhones(phones);

        List<Phone> clientPhones = clientService.getClientPhonesByUniqueId("uniqueId123");

        assertEquals(2, clientPhones.size());
        verify(clientRepository, times(1)).findByUniqueId("uniqueId123");
    }

    @Test
    void testGetDefaultClientPhonesByUniqueId() {
        Phone defaultPhone = new Phone();
        defaultPhone.setIsDefault(true);
        Phone nonDefaultPhone = new Phone();
        nonDefaultPhone.setIsDefault(false);
        client.setPhones(Arrays.asList(defaultPhone, nonDefaultPhone));

        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        List<Phone> defaultPhones = clientService.getDefaultClientPhonesByUniqueId("uniqueId123");

        assertEquals(1, defaultPhones.size());
        assertTrue(defaultPhones.get(0).getIsDefault());
        verify(clientRepository, times(1)).findByUniqueId("uniqueId123");
    }

    @Test
    void testUpdateClientPhone() {
        Phone phoneDetails = new Phone();
        phoneDetails.setId("phoneId");
        phoneDetails.setType("Home");
        phoneDetails.setNumber("987654321");
        phoneDetails.setIsDefault(false);
        phoneDetails.setState("ACT");

        Phone existingPhone = new Phone();
        existingPhone.setId("phoneId");
        existingPhone.setType("Mobile");
        existingPhone.setNumber("123456789");
        existingPhone.setIsDefault(true);
        existingPhone.setState("ACT");

        client.setPhones(Collections.singletonList(existingPhone));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Phone updatedPhone = clientService.updateClientPhone("uniqueId123", phoneDetails);

        assertNotNull(updatedPhone);
        assertEquals("Home", updatedPhone.getType());
        assertEquals("987654321", updatedPhone.getNumber());
        assertFalse(updatedPhone.getIsDefault());
        assertEquals("ACT", updatedPhone.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClientPhone() {
        Phone phone = new Phone();
        phone.setId("phoneId");
        phone.setState("ACT");

        client.setPhones(Collections.singletonList(phone));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        clientService.deleteClientPhone("uniqueId123", "phoneId");

        assertEquals("INA", phone.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testReactivateClientPhone() {
        Phone phone = new Phone();
        phone.setId("phoneId");
        phone.setState("INA");

        client.setPhones(Collections.singletonList(phone));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        clientService.reactivateClientPhone("uniqueId123", "phoneId");

        assertEquals("ACT", phone.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientAddressesByUniqueId() {
        List<Address> addresses = Arrays.asList(new Address(), new Address());
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        client.setAddresses(addresses);

        List<Address> clientAddresses = clientService.getClientAddressesByUniqueId("uniqueId123");

        assertEquals(2, clientAddresses.size());
        verify(clientRepository, times(1)).findByUniqueId("uniqueId123");
    }

    @Test
    void testGetDefaultClientAddressesByUniqueId() {
        Address defaultAddress = new Address();
        defaultAddress.setIsDefault(true);
        Address nonDefaultAddress = new Address();
        nonDefaultAddress.setIsDefault(false);
        client.setAddresses(Arrays.asList(defaultAddress, nonDefaultAddress));

        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        List<Address> defaultAddresses = clientService.getDefaultClientAddressesByUniqueId("uniqueId123");

        assertEquals(1, defaultAddresses.size());
        assertTrue(defaultAddresses.get(0).getIsDefault());
        verify(clientRepository, times(1)).findByUniqueId("uniqueId123");
    }

    @Test
    void testUpdateClientAddress() {
        Address addressDetails = new Address();
        addressDetails.setId("addressId");
        addressDetails.setType("Office");
        addressDetails.setLine1("456 Another St");
        addressDetails.setLine2("Suite 300");
        addressDetails.setLatitude(34.56789f);
        addressDetails.setLongitude(-98.76543f);
        addressDetails.setIsDefault(false);
        addressDetails.setState("ACT");

        Address existingAddress = new Address();
        existingAddress.setId("addressId");
        existingAddress.setType("Home");
        existingAddress.setLine1("123 Main St");
        existingAddress.setLine2("Apt 1");
        existingAddress.setLatitude(12.34567f);
        existingAddress.setLongitude(76.54321f);
        existingAddress.setIsDefault(true);
        existingAddress.setState("ACT");

        client.setAddresses(Collections.singletonList(existingAddress));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Address updatedAddress = clientService.updateClientAddress("uniqueId123", addressDetails);

        assertNotNull(updatedAddress);
        assertEquals("Office", updatedAddress.getType());
        assertEquals("456 Another St", updatedAddress.getLine1());
        assertEquals("Suite 300", updatedAddress.getLine2());
        assertEquals(34.56789f, updatedAddress.getLatitude());
        assertEquals(-98.76543f, updatedAddress.getLongitude());
        assertFalse(updatedAddress.getIsDefault());
        assertEquals("ACT", updatedAddress.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClientAddress() {
        Address address = new Address();
        address.setId("addressId");
        address.setState("ACT");

        client.setAddresses(Collections.singletonList(address));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        clientService.deleteClientAddress("uniqueId123", "addressId");

        assertEquals("INA", address.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testReactivateClientAddress() {
        Address address = new Address();
        address.setId("addressId");
        address.setState("INA");

        client.setAddresses(Collections.singletonList(address));
        when(clientRepository.findByUniqueId(anyString())).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        clientService.reactivateClientAddress("uniqueId123", "addressId");

        assertEquals("ACT", address.getState());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientsByIdentificationType() {
        when(clientRepository.findByIdentificationType(anyString())).thenReturn(Collections.singletonList(client));
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        List<ClientDTO> clients = clientService.getClientsByIdentificationType("ID");

        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findByIdentificationType("ID");
    }

    @Test
    void testGetClientsByCompanyName() {
        when(clientRepository.findByCompanyName(anyString())).thenReturn(Collections.singletonList(client));
        when(clientMapper.toDTO(any(Client.class))).thenReturn(clientDTO);

        List<ClientDTO> clients = clientService.getClientsByCompanyName("Company Name");

        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findByCompanyName("Company Name");
    }

}
