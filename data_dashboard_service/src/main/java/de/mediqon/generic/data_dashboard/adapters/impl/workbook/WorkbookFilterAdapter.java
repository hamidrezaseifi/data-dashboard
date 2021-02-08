package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewColumnAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookFilterAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookFilterEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookFilterDto;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataViewRepository;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookFilterAdapter
        extends ModelDtoAdapterBase<WorkbookFilterEntity, WorkbookFilterDto>
        implements IWorkbookFilterAdapter {


    private final IWorkbookDataViewAdapter workbookDataViewAdapter;
    private final IWorkbookDataViewRepository workbookDataViewRepository;

    public WorkbookFilterAdapter(IWorkbookDataViewAdapter workbookDataViewAdapter,
                                 IWorkbookDataViewRepository workbookDataViewRepository) {
        this.workbookDataViewAdapter = workbookDataViewAdapter;
        this.workbookDataViewRepository = workbookDataViewRepository;
    }

    @Override
    public WorkbookFilterEntity fromDto(WorkbookFilterDto dto) {
        WorkbookFilterEntity model = new WorkbookFilterEntity();

        model.setName(dto.getName());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setDataView(workbookDataViewRepository.getById(dto.getDataView().getId()).get());
        model.setFilterType(dto.getFilterType());
        model.setFilterKey(dto.getFilterKey());
        model.setRowIndex(dto.getRowIndex());
        model.setColumnIndex(dto.getColumnIndex());

        return model;
    }

    @Override
    public WorkbookFilterDto toDto(WorkbookFilterEntity model) {
        WorkbookFilterDto dto = new WorkbookFilterDto();

        dto.setName(model.getName());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setDataView(workbookDataViewAdapter.toDto(model.getDataView()));
        dto.setFilterType(model.getFilterType());
        dto.setFilterKey(model.getFilterKey());
        dto.setRowIndex(model.getRowIndex());
        dto.setColumnIndex(model.getColumnIndex());

        return dto;
    }
}
