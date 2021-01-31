package de.mediqon.generic.data_dashboard.repositories;


import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.entities.UserEntity;
import de.mediqon.generic.data_dashboard_common.repositories.IEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkbookRepository extends IEntityRepository<WorkbookEntity> {

    List<WorkbookEntity> getAllCustomerWorkbooks(UUID customerId);


}
