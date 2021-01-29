package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "workbook_datasource_columns")
public class WorkbookDataSourceColumnEntity extends BaseEntity {

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "column_type")
    private String columnType;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datasource_id", nullable = false)
    private WorkbookDataSourceEntity workbookDataSource;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public WorkbookDataSourceEntity getWorkbookDataSource() {
        return workbookDataSource;
    }

    public void setWorkbookDataSource(WorkbookDataSourceEntity workbookDataSource) {
        this.workbookDataSource = workbookDataSource;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
