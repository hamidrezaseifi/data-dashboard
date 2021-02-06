package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookPresentationPropertyAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationPropertyEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationPropertyDto;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookPresentationPropertyAdapter
        extends ModelDtoAdapterBase<WorkbookPresentationPropertyEntity, WorkbookPresentationPropertyDto>
        implements IWorkbookPresentationPropertyAdapter {
    

    @Override
    public WorkbookPresentationPropertyEntity fromDto(WorkbookPresentationPropertyDto dto) {
        WorkbookPresentationPropertyEntity model = new WorkbookPresentationPropertyEntity();

        model.setName(dto.getName());
        model.setValue(dto.getValue());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public WorkbookPresentationPropertyDto toDto(WorkbookPresentationPropertyEntity model) {
        WorkbookPresentationPropertyDto dto = new WorkbookPresentationPropertyDto();

        dto.setName(model.getName());
        dto.setValue(model.getValue());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
