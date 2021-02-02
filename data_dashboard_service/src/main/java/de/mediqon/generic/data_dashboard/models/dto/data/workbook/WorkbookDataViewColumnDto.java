package de.mediqon.generic.data_dashboard.models.dto.data.workbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataSourceColumnEntity;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({})
@Introspected
public class WorkbookDataViewColumnDto extends BaseDto {

     private Boolean useAsSelect;

    private String selectType;

    private WorkbookDataSourceColumnDto sourceColumn;

    public Boolean getUseAsSelect() {
        return useAsSelect;
    }

    public void setUseAsSelect(Boolean useAsSelect) {
        this.useAsSelect = useAsSelect;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public WorkbookDataSourceColumnDto getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(WorkbookDataSourceColumnDto sourceColumn) {
        this.sourceColumn = sourceColumn;
    }


}
