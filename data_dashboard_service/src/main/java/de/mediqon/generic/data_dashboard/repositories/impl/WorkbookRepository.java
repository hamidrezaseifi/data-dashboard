package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity_;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity_;
import de.mediqon.generic.data_dashboard.entities.UserEntity;
import de.mediqon.generic.data_dashboard.entities.UserEntity_;
import de.mediqon.generic.data_dashboard.repositories.IUserRepository;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookRepository;
import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
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
public class WorkbookRepository extends AEntityRdbmsHibernateRepository<WorkbookEntity> implements IWorkbookRepository {


    public WorkbookRepository(EntityManager entityManager) {
        super(WorkbookEntity.class, entityManager);
    }


    @Override
    public List<WorkbookEntity> getAllCustomerWorkbooks(UUID customerId) {
        return queryCollection((cb, root) -> (cb.equal(root.get(WorkbookEntity_.CUSTOMER).get(CustomerEntity_.ID), customerId)));
    }

}
