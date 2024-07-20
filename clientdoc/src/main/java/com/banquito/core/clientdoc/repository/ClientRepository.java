package com.banquito.core.clientdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.banquito.core.clientdoc.model.Client;
import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByIdentification(String identification);

    Client findByEmail(String email);

    Client findByFullName(String fullName);

    List<Client> findByState(String state);

    List<Client> findByIdentificationType(String identificationType);

    List<Client> findByCompanyName(String companyName);

    Client findTopByOrderByCreateDateDesc();
}
