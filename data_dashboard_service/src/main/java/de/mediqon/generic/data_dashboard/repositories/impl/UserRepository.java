package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity_;
import de.mediqon.generic.data_dashboard.entities.UserEntity;
import de.mediqon.generic.data_dashboard.entities.UserEntity_;
import de.mediqon.generic.data_dashboard.repositories.IUserRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@Repository
@TransactionalAdvice
public class UserRepository extends AEntityRdbmsHibernateRepository<UserEntity> implements IUserRepository {


    public UserRepository(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
    }


    @Override
    public List<UserEntity> getAllCustomerUsers(UUID customerId) {
        return queryCollection((cb, root) -> (cb.equal(root.get(UserEntity_.customer), customerId)));
    }

    @Override
    public Optional<UserEntity> getByUsername(String username) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.USERNAME), username)));
    }
}
