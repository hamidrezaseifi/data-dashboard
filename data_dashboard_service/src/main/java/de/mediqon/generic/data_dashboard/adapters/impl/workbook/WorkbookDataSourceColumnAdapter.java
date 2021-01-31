package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceColumnAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceColumnDto;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookDataSourceColumnAdapter
        extends ModelDtoAdapterBase<WorkbookDataSourceColumnEntity, WorkbookDataSourceColumnDto>
        implements IWorkbookDataSourceColumnAdapter {

    public WorkbookDataSourceColumnAdapter(ICustomerService customerService) {

    }

    @Override
    public WorkbookDataSourceColumnEntity fromDto(WorkbookDataSourceColumnDto dto) {
        WorkbookDataSourceColumnEntity model = new WorkbookDataSourceColumnEntity();
        model.setColumnName(dto.getColumnName());
        model.setColumnType(dto.getColumnType());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public WorkbookDataSourceColumnDto toDto(WorkbookDataSourceColumnEntity model) {
        WorkbookDataSourceColumnDto dto = new WorkbookDataSourceColumnDto();

        dto.setColumnName(model.getColumnName());
        dto.setColumnType(model.getColumnType());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
