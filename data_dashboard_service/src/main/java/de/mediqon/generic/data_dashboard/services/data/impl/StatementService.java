package de.mediqon.generic.data_dashboard.services.data.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.repositories.IStatementRepository;
import de.mediqon.generic.data_dashboard.services.data.IStatementService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class StatementService implements IStatementService {

    private final IStatementRepository statementRepository;

    public StatementService(IStatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }


    @Override
    public List<StatementEntity> getAll() {
        return statementRepository.getAll();
    }

    @Override
    public Optional<StatementEntity> getById(UUID id) {
        return statementRepository.getById(id);
    }

    @Override
    public Optional<StatementEntity> create(StatementEntity entity) {
        statementRepository.create(entity);
        return statementRepository.getById(entity.getId());
    }

    @Override
    public Optional<StatementEntity> update(StatementEntity entity) {
        statementRepository.update(entity);
        return statementRepository.getById(entity.getId());
    }

    @Override
    public void delete(StatementEntity entity) {
        statementRepository.delete(entity);
    }
}
