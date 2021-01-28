package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.dataconnection.enums.EFieldType;
import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statement_select_fields")
public class StatementSelectFieldEntity extends BaseEntity {

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_type")
    private int fieldType;

    @Column(name = "field_label")
    private String fieldLabel;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement_id", nullable = false)
    private StatementEntity statement;


    public int getFieldType() {
        return fieldType;
    }

    public EFieldType getFieldTypeEnum() {
        return EFieldType.fromId(fieldType);
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public void setFieldTypeEnum(EFieldType fieldType) {
        this.fieldType = fieldType.getId();
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public StatementEntity getStatement() {
        return statement;
    }

    public void setStatement(StatementEntity statement) {
        this.statement = statement;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLabeledFieldName() {
        String fld = fieldName;
        if(StringUtils.isNotEmpty(fieldLabel)){
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
