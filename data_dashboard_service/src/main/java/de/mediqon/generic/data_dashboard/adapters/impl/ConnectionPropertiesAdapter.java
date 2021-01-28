package de.mediqon.generic.data_dashboard.adapters.impl;

import de.mediqon.generic.data_dashboard.adapters.IConnectionPropertiesAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.ConnectionPropertiesDto;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class ConnectionPropertiesAdapter extends ModelDtoAdapterBase<ConnectionPropertiesEntity, ConnectionPropertiesDto>
        implements IConnectionPropertiesAdapter {

    public ConnectionPropertiesAdapter() {

    }

    @Override
    public ConnectionPropertiesEntity fromDto(ConnectionPropertiesDto dto) {
        ConnectionPropertiesEntity model = new ConnectionPropertiesEntity();
        model.setPort(dto.getPort());
        model.setServer(dto.getServer());
        model.setDatabaseType(dto.getDatabaseTypeInt());
        model.setDatabaseName(dto.getDatabaseName());
        model.setName(dto.getName());
        model.setStatus(dto.getStatus().getValue());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        return model;
    }

    @Override
    public ConnectionPropertiesDto toDto(ConnectionPropertiesEntity model) {
        ConnectionPropertiesDto dto = new ConnectionPropertiesDto();
        dto.setPort(model.getPort());
        dto.setServer(model.getServer());
        dto.setDatabaseTypeInt(model.getDatabaseType());
        dto.setDatabaseName(model.getDatabaseName());
        dto.setName(model.getName());
        dto.setStatusInt(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());

        return dto;
    }
}
