package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workbooks")
public class WorkbookEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workbook", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookDataSourceEntity> dataSources = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<WorkbookDataSourceEntity> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<WorkbookDataSourceEntity> dataSources) {
        this.dataSources = dataSources;
        this.dataSources.forEach(d -> d.setWorkbook(this));
    }

    public void addDataSource(WorkbookDataSourceEntity dataSource) {
        dataSource.setWorkbook(this);
        this.dataSources.add(dataSource);
    }
}
