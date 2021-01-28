package de.mediqon.generic.data_dashboard.repositories;


import de.mediqon.generic.data_dashboard_common.repositories.IEntityRepository;
import de.mediqon.generic.data_dashboard.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends IEntityRepository<UserEntity> {

    List<UserEntity> getAllCustomerUsers(UUID customerId);

    Optional<UserEntity> getByUsername(String username);
}
