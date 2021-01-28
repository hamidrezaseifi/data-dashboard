package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EFieldType;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class StatementSelectFieldDto extends BaseDto {

    private String fieldName;

    private EFieldType fieldType;

    private String fieldLabel;

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

    public void setFieldType(EFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLabeledFieldName() {
        String fld = fieldName;
        if (StringUtils.isNotEmpty(fieldLabel)) {
            fld += " as " + fieldLabel;
        }
        return fld;
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
