package de.mediqon.generic.data_dashboard.adapters;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceColumnDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDataSourceDto;
import de.mediqon.generic.data_dashboard_common.adapters.IModelDtoAdapter;

public interface IWorkbookDataSourceColumnAdapter
        extends IModelDtoAdapter<WorkbookDataSourceColumnEntity, WorkbookDataSourceColumnDto> {

}
