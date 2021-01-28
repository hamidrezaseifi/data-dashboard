package de.mediqon.generic.data_dashboard.services;


import de.mediqon.generic.data_dashboard.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerService {

    List<CustomerEntity> getAllStatusOk();

    Optional<CustomerEntity> getById(UUID id);

    Optional<CustomerEntity> getByKey(String key);

    Optional<CustomerEntity> create(CustomerEntity customerEntity);

    Optional<CustomerEntity> update(CustomerEntity customerEntity);

    void delete(CustomerEntity customerEntity);

}
