package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "workbook_dataview_columns")
public class WorkbookDataViewColumnEntity extends BaseEntity {

    @Column(name = "label")
    private String label;

    @Column(name = "user_as_select")
    private Boolean useAsSelect;

    @Column(name = "select_type")
    private String selectType;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private WorkbookDataSourceColumnEntity sourceColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataview_id", nullable = false)
    private WorkbookDataViewEntity workbookDataView;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

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

    public WorkbookDataSourceColumnEntity getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(WorkbookDataSourceColumnEntity sourceColumn) {
        this.sourceColumn = sourceColumn;
    }

    public WorkbookDataViewEntity getWorkbookDataView() {
        return workbookDataView;
    }

    public void setWorkbookDataView(WorkbookDataViewEntity workbookDataView) {
        this.workbookDataView = workbookDataView;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
