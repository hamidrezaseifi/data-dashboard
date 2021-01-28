package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EFieldType;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class StatementHavingFieldDto extends BaseDto {

    private String fieldName;

    private EFieldType fieldType;

    private String fieldValue;

    protected Integer status = 1;

    public EFieldType getFieldType() {
        return fieldType;
    }

    public EFieldType getFieldTypeEnum() {
        return fieldType;
    }

    public void setFieldTypeInt(int fieldType) {
        this.fieldType = EFieldType.fromId(fieldType);
    }

    public void setFieldTypeEnum(EFieldType fieldType) {
        this.fieldType = fieldType;
    }


    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
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
