package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity_;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity_;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataSourceColumnRepository;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookRepository;
import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Singleton
@Repository
@TransactionalAdvice
public class WorkbookDataSourceColumnRepository
        extends AEntityRdbmsHibernateRepository<WorkbookDataSourceColumnEntity>
        implements IWorkbookDataSourceColumnRepository {


    public WorkbookDataSourceColumnRepository(EntityManager entityManager) {
        super(WorkbookDataSourceColumnEntity.class, entityManager);
    }


}
