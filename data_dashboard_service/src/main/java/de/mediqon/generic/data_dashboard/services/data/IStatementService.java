package de.mediqon.generic.data_dashboard.services.data;

import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStatementService {

    List<StatementEntity> getAll();

    Optional<StatementEntity> getById(UUID id);

    Optional<StatementEntity> create(StatementEntity entity);

    Optional<StatementEntity> update(StatementEntity entity);

    void delete(StatementEntity entity);
}
