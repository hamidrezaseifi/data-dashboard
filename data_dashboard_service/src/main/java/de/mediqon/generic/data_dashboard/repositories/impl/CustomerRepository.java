package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity_;
import de.mediqon.generic.data_dashboard.repositories.ICustomerRepository;
import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@Repository
@TransactionalAdvice
public class CustomerRepository extends AEntityRdbmsHibernateRepository<CustomerEntity>
        implements ICustomerRepository {

    public CustomerRepository(EntityManager entityManager) {
        super(CustomerEntity.class, entityManager);
    }

    @Override
    @TransactionalAdvice(readOnly = true)
    public List<CustomerEntity> getAllStatusOk() {
        List<CustomerEntity> all = super.getAll();

        return all.stream().filter(c -> c.getStatus() == 1).collect(Collectors.toList());
    }

    @Override
    @TransactionalAdvice(readOnly = true)
    public Optional<CustomerEntity> getByKey(String key) {
        return queryScala((cb, root) -> (cb.equal(root.get(CustomerEntity_.CUSTOMER_KEY), key)));
    }
}
