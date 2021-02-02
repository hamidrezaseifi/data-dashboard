package de.mediqon.generic.data_dashboard.adapters;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewColumnEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceColumnDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewColumnDto;
import de.mediqon.generic.data_dashboard_common.adapters.IModelDtoAdapter;

public interface IWorkbookDataViewColumnAdapter
        extends IModelDtoAdapter<WorkbookDataViewColumnEntity, WorkbookDataViewColumnDto> {

}
