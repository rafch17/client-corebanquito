package com.banquito.corecobros.clientdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.banquito.corecobros.clientdoc.model.Address;

import java.util.List;

public interface ClientAddressRepository extends MongoRepository<Address, String> {
    List<Address> findByClientId(String clientId);

    List<Address> findByClientIdAndIsDefault(String clientId, boolean isDefault);
}
