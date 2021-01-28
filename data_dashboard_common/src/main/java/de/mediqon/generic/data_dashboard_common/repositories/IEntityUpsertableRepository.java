package de.mediqon.generic.data_dashboard_common.repositories;


public interface IEntityUpsertableRepository<TEntity> extends IEntityRepository<TEntity> {
    void upsert(TEntity entity);
}
