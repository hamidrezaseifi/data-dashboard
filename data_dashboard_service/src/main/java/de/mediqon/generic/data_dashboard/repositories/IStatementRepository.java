package de.mediqon.generic.data_dashboard.repositories;


import de.mediqon.generic.data_dashboard_common.repositories.IEntityRepository;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;

import java.util.Optional;

public interface IStatementRepository extends IEntityRepository<StatementEntity> {

    Optional<StatementEntity> getByName(String name);
}
