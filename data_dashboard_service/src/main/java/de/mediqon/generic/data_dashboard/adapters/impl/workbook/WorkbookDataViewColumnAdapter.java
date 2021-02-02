package de.mediqon.generic.data_dashboard.adapters.impl.workbook;

import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataSourceColumnAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookDataViewColumnAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewColumnEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewColumnEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewColumnDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewColumnDto;
import de.mediqon.generic.data_dashboard.repositories.IWorkbookDataSourceColumnRepository;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;

@Singleton
public class WorkbookDataViewColumnAdapter
        extends ModelDtoAdapterBase<WorkbookDataViewColumnEntity, WorkbookDataViewColumnDto>
        implements IWorkbookDataViewColumnAdapter {

    private final IWorkbookDataSourceColumnRepository workbookDataSourceColumnRepository;
    private final IWorkbookDataSourceColumnAdapter workbookDataSourceColumnAdapter;

    public WorkbookDataViewColumnAdapter(IWorkbookDataSourceColumnRepository workbookDataSourceColumnRepository,
                                         IWorkbookDataSourceColumnAdapter workbookDataSourceColumnAdapter) {
        this.workbookDataSourceColumnRepository = workbookDataSourceColumnRepository;
        this.workbookDataSourceColumnAdapter = workbookDataSourceColumnAdapter;
    }

    @Override
    public WorkbookDataViewColumnEntity fromDto(WorkbookDataViewColumnDto dto) {
        WorkbookDataViewColumnEntity model = new WorkbookDataViewColumnEntity();

        model.setSelectType(dto.getSelectType());
        model.setSourceColumn(workbookDataSourceColumnRepository.getById(dto.getSourceColumn().getId()).get());
        model.setUseAsSelect(dto.getUseAsSelect());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public WorkbookDataViewColumnDto toDto(WorkbookDataViewColumnEntity model) {
        WorkbookDataViewColumnDto dto = new WorkbookDataViewColumnDto();

        dto.setSelectType(model.getSelectType());
        dto.setSourceColumn(workbookDataSourceColumnAdapter.toDto(model.getSourceColumn()));
        dto.setUseAsSelect(model.getUseAsSelect());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
