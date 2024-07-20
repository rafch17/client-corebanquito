package com.banquito.core.clientdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.banquito.core.clientdoc.model.Phone;

import java.util.List;

public interface ClientPhoneRepository extends MongoRepository<Phone, String> {
    List<Phone> findByClientId(String clientId);

    List<Phone> findByClientIdAndIsDefault(String clientId, boolean isDefault);
}
