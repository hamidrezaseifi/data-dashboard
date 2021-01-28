package de.mediqon.generic.data_dashboard.repositories;


import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard_common.repositories.IEntityRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends IEntityRepository<CustomerEntity> {

    List<CustomerEntity> getAllStatusOk();

    Optional<CustomerEntity> getByKey(String key);
}
