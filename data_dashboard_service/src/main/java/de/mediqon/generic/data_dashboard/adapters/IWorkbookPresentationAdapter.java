package de.mediqon.generic.data_dashboard.adapters;

import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationPropertyEntity;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookPresentationPropertyDto;
import de.mediqon.generic.data_dashboard_common.adapters.IModelDtoAdapter;

public interface IWorkbookPresentationAdapter
        extends IModelDtoAdapter<WorkbookPresentationEntity, WorkbookPresentationDto> {

}
