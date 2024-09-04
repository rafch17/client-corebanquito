package com.banquito.core.clientdoc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.banquito.core.clientdoc.dto.ClientDTO;
import com.banquito.core.clientdoc.model.Address;
import com.banquito.core.clientdoc.model.Phone;
import com.banquito.core.clientdoc.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientDTO clientDTO;
    private Address address;
    private Phone phone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clientDTO = ClientDTO.builder()
                .id("1")
                .codeSegment("codeSegment")
                .nameSegment("nameSegment")
                .uniqueId("uniqueId")
                .clientType("clientType")
                .identificationType("identificationType")
                .identification("identification")
                .firstName("First")
                .lastName("Last")
                .email("email@example.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .companyName("Company")
                .companyType("Type")
                .state("Active")
                .createDate(LocalDateTime.now())
                .lastStateDate(LocalDateTime.now())
                .nationality("Nationality")
                .maritalState("Single")
                .monthlyAverageIncome(BigDecimal.valueOf(1000))
                .notes("Notes")
                .phones(Collections.emptyList())
                .addresses(Collections.emptyList())
                .build();

        address = new Address();
        address.setId("addressId");
        address.setType("Home");
        address.setLine1("Line1");
        address.setLine2("Line2");
        address.setLatitude(0.0f);
        address.setLongitude(0.0f);
        address.setIsDefault(true);
        address.setState("Active");

        phone = new Phone();
        phone.setId("phoneId");
        phone.setType("Mobile");
        phone.setNumber("1234567890");
        phone.setIsDefault(true);
        phone.setState("Active");
    }

    @Test
    void testCreateClient() throws Exception {
        when(clientService.createClient(any(ClientDTO.class))).thenReturn(clientDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/client-microservice/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("First"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Last"));

        verify(clientService, times(1)).createClient(any(ClientDTO.class));
    }
    
    @Test
    void testGetClientByUniqueId() throws Exception {
        when(clientService.getClientByUniqueId(anyString())).thenReturn(clientDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client-microservice/api/v1/clients/uniqueId")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("First"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Last"));

        verify(clientService, times(1)).getClientByUniqueId("uniqueId");
    }

    @Test
    void testUpdateClient() throws Exception {
        when(clientService.updateClient(anyString(), any(ClientDTO.class))).thenReturn(clientDTO);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/client-microservice/api/v1/clients/uniqueId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("First"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Last"));

        verify(clientService, times(1)).updateClient("uniqueId", clientDTO);
    }

    @Test
    void testDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient(anyString());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/client-microservice/api/v1/clients/uniqueId")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());

        verify(clientService, times(1)).deleteClient("uniqueId");
    }

    @Test
    void testUpdateClientAddress() throws Exception {
        when(clientService.updateClientAddress(anyString(), any(Address.class))).thenReturn(address);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/client-microservice/api/v1/clients/uniqueId/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(address)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("addressId"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Home"));

        verify(clientService, times(1)).updateClientAddress("uniqueId", address);
    }

    @Test
    void testGetClientAddressesByUniqueId() throws Exception {
        List<Address> addresses = Collections.singletonList(address);
        when(clientService.getClientAddressesByUniqueId(anyString())).thenReturn(addresses);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client-microservice/api/v1/clients/uniqueId/addresses")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("addressId"))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Home"));

        verify(clientService, times(1)).getClientAddressesByUniqueId("uniqueId");
    }

    @Test
    void testDeleteClientAddress() throws Exception {
        doNothing().when(clientService).deleteClientAddress(anyString(), anyString());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/client-microservice/api/v1/clients/uniqueId/addresses/addressId")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());

        verify(clientService, times(1)).deleteClientAddress("uniqueId", "addressId");
    }

    @Test
    void testGetDefaultClientAddressesByUniqueId() throws Exception {
        List<Address> addresses = Collections.singletonList(address);
        when(clientService.getDefaultClientAddressesByUniqueId(anyString())).thenReturn(addresses);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client-microservice/api/v1/clients/uniqueId/addresses/default")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("addressId"))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Home"));

        verify(clientService, times(1)).getDefaultClientAddressesByUniqueId("uniqueId");
    }

    @Test
    void testReactivateClient() throws Exception {
        doNothing().when(clientService).reactivateClient(anyString());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/client-microservice/api/v1/clients/reactivate/uniqueId")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());

        verify(clientService, times(1)).reactivateClient("uniqueId");
    }
}
