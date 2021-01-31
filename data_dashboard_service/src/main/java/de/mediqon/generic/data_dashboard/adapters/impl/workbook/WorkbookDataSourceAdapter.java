package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceColumnAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceDto;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookDataSourceAdapter
        extends ModelDtoAdapterBase<WorkbookDataSourceEntity, WorkbookDataSourceDto>
        implements IWorkbookDataSourceAdapter {

    private final IWorkbookDataSourceColumnAdapter workbookDataSourceColumnAdapter;

    public WorkbookDataSourceAdapter(IWorkbookDataSourceColumnAdapter workbookDataSourceColumnAdapter) {
        this.workbookDataSourceColumnAdapter = workbookDataSourceColumnAdapter;
    }

    @Override
    public WorkbookDataSourceEntity fromDto(WorkbookDataSourceDto dto) {
        WorkbookDataSourceEntity model = new WorkbookDataSourceEntity();
        model.setConnectionId(dto.getConnectionId());
        model.setColumns(workbookDataSourceColumnAdapter.fromDtoList(dto.getColumns()));
        model.setDataSource(dto.getDataSource());
        model.setDataSourceType(dto.getDataSourceType());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public WorkbookDataSourceDto toDto(WorkbookDataSourceEntity model) {
        WorkbookDataSourceDto dto = new WorkbookDataSourceDto();

        dto.setConnectionId(model.getConnectionId());
        dto.setColumns(workbookDataSourceColumnAdapter.toDtoList(model.getColumns()));
        dto.setDataSource(model.getDataSource());
        dto.setDataSourceType(model.getDataSourceType());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
