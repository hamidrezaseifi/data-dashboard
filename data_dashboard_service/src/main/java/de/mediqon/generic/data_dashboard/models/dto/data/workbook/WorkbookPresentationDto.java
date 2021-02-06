package de.mediqon.generic.data_dashboard.models.dto.data.workbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookPresentationType;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({})
@Introspected
public class WorkbookPresentationDto extends BaseDto {

    private String name;

    private EWorkbookPresentationType presentationType;

    private String presentationStyle;

    protected Integer status = 1;

    private WorkbookDataViewDto dataView;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EWorkbookPresentationType getPresentationType() {
        return presentationType;
    }

    public void setPresentationType(EWorkbookPresentationType presentationType) {
        this.presentationType = presentationType;
    }

    public String getPresentationStyle() {
        return presentationStyle;
    }

    public void setPresentationStyle(String presentationStyle) {
        this.presentationStyle = presentationStyle;
    }

    public WorkbookDataViewDto getDataView() {
        return dataView;
    }

    public void setDataView(WorkbookDataViewDto dataView) {
        this.dataView = dataView;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
