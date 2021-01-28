package de.mediqon.generic.data_dashboard.services.data.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.repositories.IConnectionPropertiesRepository;
import de.mediqon.generic.data_dashboard.services.data.IConnectionPropertiesService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class ConnectionPropertiesService implements IConnectionPropertiesService {

    private final IConnectionPropertiesRepository connectionPropertiesRepository;

    public ConnectionPropertiesService(IConnectionPropertiesRepository connectionPropertiesRepository) {
        this.connectionPropertiesRepository = connectionPropertiesRepository;
    }


    @Override
    public List<ConnectionPropertiesEntity> getAll() {
        return connectionPropertiesRepository.getAll();
    }

    @Override
    public Optional<ConnectionPropertiesEntity> getById(UUID id) {
        return connectionPropertiesRepository.getById(id);
    }

    @Override
    public Optional<ConnectionPropertiesEntity> create(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.create(entity);
        return connectionPropertiesRepository.getById(entity.getId());
    }

    @Override
    public Optional<ConnectionPropertiesEntity> update(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.update(entity);
        return connectionPropertiesRepository.getById(entity.getId());
    }

    @Override
    public void delete(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.delete(entity);
    }
}
