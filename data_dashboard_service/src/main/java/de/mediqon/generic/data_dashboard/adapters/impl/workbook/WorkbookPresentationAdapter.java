package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookFilterAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookPresentationAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookPresentationPropertyAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookPresentationType;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataViewRepository;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookPresentationAdapter
        extends ModelDtoAdapterBase<WorkbookPresentationEntity, WorkbookPresentationDto>
        implements IWorkbookPresentationAdapter {


    private final IWorkbookPresentationPropertyAdapter workbookPresentationPropertyAdapter;
    private final IWorkbookDataViewRepository workbookDataViewRepository;
    private final IWorkbookDataViewAdapter workbookDataViewAdapter;

    public WorkbookPresentationAdapter(IWorkbookPresentationPropertyAdapter workbookPresentationPropertyAdapter,
                                       IWorkbookDataViewRepository workbookDataViewRepository,
                                       IWorkbookDataViewAdapter workbookDataViewAdapter) {
        this.workbookPresentationPropertyAdapter = workbookPresentationPropertyAdapter;
        this.workbookDataViewRepository = workbookDataViewRepository;
        this.workbookDataViewAdapter = workbookDataViewAdapter;
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
        model.setDataView(workbookDataViewRepository.getById(dto.getDataView().getId()).get());

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
        dto.setDataView(workbookDataViewAdapter.toDto(model.getDataView()));

        return dto;
    }
}
