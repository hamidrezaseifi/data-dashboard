package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataSourceColumnRepository;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataViewRepository;
import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
@Repository
@TransactionalAdvice
public class WorkbookDataViewRepository
        extends AEntityRdbmsHibernateRepository<WorkbookDataViewEntity>
        implements IWorkbookDataViewRepository {


    public WorkbookDataViewRepository(EntityManager entityManager) {
        super(WorkbookDataViewEntity.class, entityManager);
    }


}
