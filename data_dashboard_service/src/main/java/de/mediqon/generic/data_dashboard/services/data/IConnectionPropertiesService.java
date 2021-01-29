package de.mediqon.generic.data_dashboard.services.data;

import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.ColumnDetails;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IConnectionPropertiesService {

    List<ConnectionPropertiesEntity> getAll();

    Optional<ConnectionPropertiesEntity> getById(UUID id);

    Optional<ConnectionPropertiesEntity> create(ConnectionPropertiesEntity entity);

    Optional<ConnectionPropertiesEntity> update(ConnectionPropertiesEntity entity);

    void delete(ConnectionPropertiesEntity entity);

    List<String> getConnectionTableList(UUID id) throws SQLException;

    List<ColumnDetails> getConnectionTableColumnList(UUID id, String tableName) throws SQLException;
}
