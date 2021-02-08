package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookFilterAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookPresentationAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookPresentationPropertyAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookDataSourceType;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookPresentationType;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataViewRepository;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookPresentationAdapter
        extends ModelDtoAdapterBase<WorkbookPresentationEntity, WorkbookPresentationDto>
        implements IWorkbookPresentationAdapter {


    private final IWorkbookPresentationPropertyAdapter workbookPresentationPropertyAdapter;
    private final IWorkbookFilterAdapter workbookFilterAdapter;

    public WorkbookPresentationAdapter(IWorkbookPresentationPropertyAdapter workbookPresentationPropertyAdapter,
                                       IWorkbookFilterAdapter workbookFilterAdapter) {
        this.workbookPresentationPropertyAdapter = workbookPresentationPropertyAdapter;
        this.workbookFilterAdapter = workbookFilterAdapter;
    }

    @Override
    public WorkbookPresentationEntity fromDto(WorkbookPresentationDto dto) {
        WorkbookPresentationEntity model = new WorkbookPresentationEntity();

        model.setName(dto.getName());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setPresentationType(dto.getPresentationType().getId());
        model.setPresentationStyle(dto.getPresentationStyle());
        model.setProperties(workbookPresentationPropertyAdapter.fromDtoList(dto.getProperties()));
        model.setDataSourceType(dto.getDataSourceType().getId());
        model.setDataSourceId(dto.getDataSourceId());
        model.setRowIndex(dto.getRowIndex());
        model.setColumnIndex(dto.getColumnIndex());
        model.setFilters(workbookFilterAdapter.fromDtoList(dto.getFilters()));

        return model;
    }

    @Override
    public WorkbookPresentationDto toDto(WorkbookPresentationEntity model) {
        WorkbookPresentationDto dto = new WorkbookPresentationDto();

        dto.setName(model.getName());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setPresentationType(EWorkbookPresentationType.fromId(model.getPresentationType()));
        dto.setPresentationStyle(model.getPresentationStyle());
        dto.setProperties(workbookPresentationPropertyAdapter.toDtoList(model.getProperties()));
        dto.setDataSourceType(EWorkbookDataSourceType.fromId(model.getDataSourceType()));
        dto.setDataSourceId(model.getDataSourceId());
        dto.setRowIndex(model.getRowIndex());
        dto.setColumnIndex(model.getColumnIndex());
        dto.setFilters(workbookFilterAdapter.toDtoList(model.getFilters()));

        return dto;
    }
}
