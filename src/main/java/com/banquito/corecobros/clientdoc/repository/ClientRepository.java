package com.banquito.corecobros.clientdoc.repository;

import com.banquito.corecobros.clientdoc.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByUniqueId(String uniqueId);

    Client findByIdentification(String identification);

    Client findByEmail(String email);

    Client findByFullName(String fullName);

    List<Client> findByIdentificationType(String identificationType);

    List<Client> findByCompanyName(String companyName);

    Client findTopByOrderByCreateDateDesc();
}
