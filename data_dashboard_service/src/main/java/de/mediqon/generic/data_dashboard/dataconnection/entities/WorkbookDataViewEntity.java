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
@Table(name = "workbook_dataviews")
public class WorkbookDataViewEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workbook_id", nullable = false)
    private WorkbookEntity workbook;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workbookDataView", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookDataViewColumnEntity> columns = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<WorkbookDataViewColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<WorkbookDataViewColumnEntity> columns) {
        this.columns = columns;
        this.columns.forEach(c -> c.setWorkbookDataView(this));
    }

    public void addColumn(WorkbookDataViewColumnEntity column) {
        column.setWorkbookDataView(this);
        this.columns.add(column);
    }
}
