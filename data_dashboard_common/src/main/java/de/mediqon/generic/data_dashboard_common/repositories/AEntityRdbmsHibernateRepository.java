package de.mediqon.generic.data_dashboard_common.repositories;

import de.mediqon.generic.data_dashboard_common.exceptions.EntityAlreadyExistsException;
import de.mediqon.generic.data_dashboard_common.exceptions.EntityPropertyMissingException;
import de.mediqon.generic.data_dashboard_common.exceptions.LostUpdateException;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.mediqon.generic.data_dashboard_common.repositories.HibernatePredicateQueryAdapter.OrderBuilder;
import de.mediqon.generic.data_dashboard_common.repositories.HibernatePredicateQueryAdapter.PredicateBuilder;


@Repository
public abstract class AEntityRdbmsHibernateRepository<TEntity>
        implements IEntityRepository<TEntity> {

    @PersistenceContext
    protected final EntityManager entityManager;

    protected final Class<TEntity> entityType;

    protected final HibernatePredicateQueryAdapter hibernatePredicateQueryAdapter;

    public AEntityRdbmsHibernateRepository(Class<TEntity> entityType,
                                           EntityManager entityManager) {
        super();

        this.entityType = entityType;
        this.entityManager = entityManager;

        this.hibernatePredicateQueryAdapter = new HibernatePredicateQueryAdapter();
    }

    @Override
    @TransactionalAdvice(readOnly = true)
    public List<TEntity> getAll() {
        return queryCollection();
    }

    @Override
    @TransactionalAdvice(readOnly = true)
    public Optional<TEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Override
    @TransactionalAdvice(readOnly = false)
    public void create(TEntity entity) {

        try {
            entityManager.persist(entity);
            entityManager.flush();
        } catch (PersistenceException exception) {
            Throwable innerException = exception.getCause();

            if (   innerException != null
                && innerException instanceof ConstraintViolationException) {

                ConstraintViolationException constraintException = (ConstraintViolationException) innerException;
                String constraintName = constraintException.getConstraintName();

                if (constraintName != null
                    && (constraintName.replace("\"", "").toLowerCase().startsWith("pk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("public.pk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("uk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("public.uk"))) {

                    throw new EntityAlreadyExistsException(
                            "Failed to persist entity since id / name entity already taken", exception);
                }

                throw new EntityPropertyMissingException("Failed to persist entity since missing required fields",
                                                         exception);
            }

            throw exception;
        }
    }

    @Override
    @TransactionalAdvice(readOnly = false)
    public void update(TEntity entity) {
        try{
            //entityManager.refresh(entity);

            entityManager.merge(entity);
            entityManager.flush();
        }
        catch (OptimisticLockException exception){
                throw new LostUpdateException("The Entity you trying to Update is Outdated please refresh and try it again", exception);
        }
    }

    @Override
    //@Transactional
    public void delete(TEntity entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    protected Optional<TEntity> queryScala(PredicateBuilder<TEntity> predicateBuilder) {
        return hibernatePredicateQueryAdapter.queryScala(entityManager, entityType, predicateBuilder);
    }

    protected Optional<TEntity> queryScala(PredicateBuilder<TEntity> predicateBuilder,
                                           OrderBuilder<TEntity> orderBuilder) {
        return hibernatePredicateQueryAdapter.queryScala(entityManager, entityType, predicateBuilder, orderBuilder);
    }

    protected List<TEntity> queryCollection() {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, null);
    }

    protected List<TEntity> queryCollection(PredicateBuilder<TEntity> predicateBuilder) {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, predicateBuilder);
    }

    protected List<TEntity> queryCollection(PredicateBuilder<TEntity> predicateBuilder,
                                            OrderBuilder<TEntity> orderBuilder) {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, predicateBuilder, orderBuilder);
    }

    protected int queryCountItems(PredicateBuilder<TEntity> predicateBuilder) {

        return hibernatePredicateQueryAdapter.queryCountItems(entityManager, entityType, predicateBuilder);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected int deleteCollection(PredicateBuilder<TEntity> predicateBuilder) {

        return hibernatePredicateQueryAdapter.deleteCollection(entityManager, entityType, predicateBuilder);
    }
}