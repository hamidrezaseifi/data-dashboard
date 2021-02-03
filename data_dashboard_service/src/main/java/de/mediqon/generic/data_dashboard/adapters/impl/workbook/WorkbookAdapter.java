package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.*;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDto;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard.services.data.IWorkbookService;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookAdapter extends ModelDtoAdapterBase<WorkbookEntity, WorkbookDto> implements IWorkbookAdapter {

    private final ICustomerService customerService;
    private final IWorkbookDataSourceAdapter workbookDataSourceAdapter;
    private final IWorkbookDataViewAdapter workbookDataViewAdapter;
    private final IWorkbookFilterAdapter workbookFilterAdapter;

    public WorkbookAdapter(ICustomerService customerService,
                           IWorkbookDataSourceAdapter workbookDataSourceAdapter,
                           IWorkbookDataViewAdapter workbookDataViewAdapter,
                           IWorkbookFilterAdapter workbookFilterAdapter) {
        this.customerService = customerService;
        this.workbookDataSourceAdapter = workbookDataSourceAdapter;
        this.workbookDataViewAdapter = workbookDataViewAdapter;
        this.workbookFilterAdapter = workbookFilterAdapter;
    }

    @Override
    public WorkbookEntity fromDto(WorkbookDto dto) {
        WorkbookEntity model = new WorkbookEntity();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setCustomer(customerService.getById(dto.getCustomerId()).get());
        model.setDataSources(workbookDataSourceAdapter.fromDtoList(dto.getDataSources()));
        model.setDataViews(workbookDataViewAdapter.fromDtoList(dto.getDataViews()));
        model.setFilters(workbookFilterAdapter.fromDtoList(dto.getFilters()));

        return model;
    }

    @Override
    public WorkbookDto toDto(WorkbookEntity model) {
        WorkbookDto dto = new WorkbookDto();

        dto.setName(model.getName());
        dto.setCustomerId(model.getCustomer().getId());
        dto.setDescription(model.getDescription());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setCustomerName(model.getCustomer().getName());
        dto.setDataSources(workbookDataSourceAdapter.toDtoList(model.getDataSources()));
        dto.setDataViews(workbookDataViewAdapter.toDtoList(model.getDataViews()));
        dto.setFilters(workbookFilterAdapter.toDtoList(model.getFilters()));

        return dto;
    }
}
