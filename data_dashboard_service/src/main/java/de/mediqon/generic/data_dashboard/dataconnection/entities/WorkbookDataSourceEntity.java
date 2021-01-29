package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workbook_datasources")
public class WorkbookDataSourceEntity extends BaseEntity {

    @Column(name = "connection_id")
    private UUID connectionId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workbook_id", nullable = false)
    private WorkbookEntity workbook;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workbookDataSource", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookDataSourceColumnEntity> columns = new ArrayList<>();

    public UUID getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(UUID connectionId) {
        this.connectionId = connectionId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public WorkbookEntity getWorkbook() {
        return workbook;
    }

    public void setWorkbook(WorkbookEntity workbook) {
        this.workbook = workbook;
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

    public List<WorkbookDataSourceColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<WorkbookDataSourceColumnEntity> columns) {
        this.columns = columns;
        this.columns.forEach(c -> c.setWorkbookDataSource(this));
    }

    public void addColumn(WorkbookDataSourceColumnEntity column) {
        column.setWorkbookDataSource(this);
        this.columns.add(column);
    }
}
