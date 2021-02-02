package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewColumnAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewDto;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookDataViewAdapter 
        extends ModelDtoAdapterBase<WorkbookDataViewEntity, WorkbookDataViewDto> 
        implements IWorkbookDataViewAdapter {


    private final IWorkbookDataViewColumnAdapter workbookDataViewColumnAdapter;

    public WorkbookDataViewAdapter(IWorkbookDataViewColumnAdapter workbookDataViewColumnAdapter) {

        this.workbookDataViewColumnAdapter = workbookDataViewColumnAdapter;
    }

    @Override
    public WorkbookDataViewEntity fromDto(WorkbookDataViewDto dto) {
        WorkbookDataViewEntity model = new WorkbookDataViewEntity();
        model.setName(dto.getName());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setColumns(workbookDataViewColumnAdapter.fromDtoList(dto.getColumns()));

        return model;
    }

    @Override
    public WorkbookDataViewDto toDto(WorkbookDataViewEntity model) {
        WorkbookDataViewDto dto = new WorkbookDataViewDto();

        dto.setName(model.getName());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setColumns(workbookDataViewColumnAdapter.toDtoList(model.getColumns()));

        return dto;
    }
}
