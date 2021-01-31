package de.mediqon.generic.data_dashboard.services.data.impl;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookRepository;
import de.mediqon.generic.data_dashboard.services.data.IWorkbookService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class WorkbookService implements IWorkbookService {

    private final IWorkbookRepository workbookRepository;

    public WorkbookService(IWorkbookRepository workbookRepository) {
        this.workbookRepository = workbookRepository;
    }

    @Override
    public List<WorkbookEntity> getAll() {
        return workbookRepository.getAll();
    }

    @Override
    public Optional<WorkbookEntity> getById(UUID id) {
        return workbookRepository.getById(id);
    }

    @Override
    public Optional<WorkbookEntity> create(WorkbookEntity entity) {
        workbookRepository.create(entity);
        return workbookRepository.getById(entity.getId());
    }

    @Override
    public Optional<WorkbookEntity> update(WorkbookEntity entity) {
        workbookRepository.update(entity);
        return workbookRepository.getById(entity.getId());
    }

    @Override
    public void delete(WorkbookEntity entity) {
        workbookRepository.delete(entity);
    }

    @Override
    public List<WorkbookEntity> getAllCustomerWorkbooks(UUID customerId) {
        return workbookRepository.getAllCustomerWorkbooks(customerId);
    }
}
