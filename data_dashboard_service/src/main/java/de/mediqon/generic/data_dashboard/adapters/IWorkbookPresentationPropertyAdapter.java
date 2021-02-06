package de.mediqon.generic.data_dashboard.adapters;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookFilterEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationPropertyEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookFilterDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationPropertyDto;
import de.mediqon.generic.data_dashboard_common.adapters.IModelDtoAdapter;

public interface IWorkbookPresentationPropertyAdapter
        extends IModelDtoAdapter<WorkbookPresentationPropertyEntity, WorkbookPresentationPropertyDto> {

}
