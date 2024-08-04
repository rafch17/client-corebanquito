package com.banquito.core.clientdoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.clientdoc.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByUniqueId(String uniqueId);

    Client findByIdentification(String identification);

    Client findByEmail(String email);

    Client findByFullName(String fullName);

    List<Client> findByIdentificationType(String identificationType);

    List<Client> findByCompanyName(String companyName);

    Client findTopByOrderByCreateDateDesc();
}
