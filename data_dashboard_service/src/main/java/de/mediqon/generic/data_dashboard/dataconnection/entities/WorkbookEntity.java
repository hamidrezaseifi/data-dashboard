package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
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

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workbook", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookDataSourceEntity> dataSources = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workbook", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookDataViewEntity> dataViews = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getUpdatedAt() {
        return updatedAt;
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

    public List<WorkbookDataViewEntity> getDataViews() {
        return dataViews;
    }

    public void setDataViews(List<WorkbookDataViewEntity> dataViews) {
        this.dataViews = dataViews;
        this.dataViews.forEach(v -> v.setWorkbook(this));
    }

    public void addDataView(WorkbookDataViewEntity dataViewEntity) {
        dataViewEntity.setWorkbook(this);
        this.dataViews.add(dataViewEntity);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
