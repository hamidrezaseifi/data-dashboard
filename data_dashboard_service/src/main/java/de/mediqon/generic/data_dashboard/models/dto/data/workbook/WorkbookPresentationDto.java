package de.mediqon.generic.data_dashboard.models.dto.data.workbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookPresentationPropertyEntity;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookDataSourceType;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums.EWorkbookPresentationType;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({})
@Introspected
public class WorkbookPresentationDto extends BaseDto {

    private String name;

    private EWorkbookPresentationType presentationType;

    private String presentationStyle;

    private EWorkbookDataSourceType dataSourceType;

    private UUID dataSourceId;

    protected Integer status = 1;

    private List<WorkbookPresentationPropertyDto> properties = new ArrayList<>();

    private List<WorkbookFilterDto> filters = new ArrayList<>();


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

    public EWorkbookDataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(EWorkbookDataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public UUID getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(UUID dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<WorkbookPresentationPropertyDto> getProperties() {
        return properties;
    }

    public void setProperties(List<WorkbookPresentationPropertyDto> properties) {
        this.properties = properties;
    }

    public List<WorkbookFilterDto> getFilters() {
        return filters;
    }

    public void setFilters(List<WorkbookFilterDto> filters) {
        this.filters = filters;
    }
}
