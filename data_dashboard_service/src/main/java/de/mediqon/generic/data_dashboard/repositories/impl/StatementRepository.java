package de.mediqon.generic.data_dashboard.repositories.impl;

import de.mediqon.generic.data_dashboard_common.repositories.AEntityRdbmsHibernateRepository;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity_;
import de.mediqon.generic.data_dashboard.entities.UserEntity_;
import de.mediqon.generic.data_dashboard.repositories.IStatementRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
@Repository
@TransactionalAdvice
public class StatementRepository extends AEntityRdbmsHibernateRepository<StatementEntity> implements
                                                                                          IStatementRepository {


    public StatementRepository(EntityManager entityManager) {
        super(StatementEntity.class, entityManager);
    }


    @Override
    public Optional<StatementEntity> getByName(String name) {
        return queryScala((cb, root) -> (cb.equal(root.get(StatementEntity_.NAME), name)));
    }
}
