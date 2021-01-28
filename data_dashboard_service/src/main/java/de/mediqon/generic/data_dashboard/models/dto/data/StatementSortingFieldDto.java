package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class StatementSortingFieldDto extends BaseDto {

    private String fieldName;

    private String sortType;

    protected Integer status = 1;

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
