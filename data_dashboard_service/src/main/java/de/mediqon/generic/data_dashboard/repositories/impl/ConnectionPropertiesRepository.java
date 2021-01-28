package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.repositories.IConnectionPropertiesRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
@Repository
@TransactionalAdvice
public class ConnectionPropertiesRepository
        extends AEntityRdbmsHibernateRepository<ConnectionPropertiesEntity>
        implements IConnectionPropertiesRepository {


    public ConnectionPropertiesRepository(EntityManager entityManager) {
        super(ConnectionPropertiesEntity.class, entityManager);
    }



}
