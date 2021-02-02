package de.mediqon.generic.data_dashboard.adapters;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataViewDto;
import de.mediqon.generic.data_dashboard_common.adapters.IModelDtoAdapter;

public interface IWorkbookDataViewAdapter extends IModelDtoAdapter<WorkbookDataViewEntity, WorkbookDataViewDto> {

}
