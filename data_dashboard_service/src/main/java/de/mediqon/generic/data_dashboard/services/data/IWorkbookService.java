package de.mediqon.generic.data_dashboard.services.data;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkbookService {

    List<WorkbookEntity> getAll();

    Optional<WorkbookEntity> getById(UUID id);

    Optional<WorkbookEntity> create(WorkbookEntity entity);

    Optional<WorkbookEntity> update(WorkbookEntity entity);

    void delete(WorkbookEntity entity);

    List<WorkbookEntity> getAllCustomerWorkbooks(UUID customerId);
}
