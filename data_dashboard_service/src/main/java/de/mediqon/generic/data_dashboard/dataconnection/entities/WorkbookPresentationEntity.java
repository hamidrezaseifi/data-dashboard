package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workbook_presentations")
public class WorkbookPresentationEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "presentation_type")
    private String presentationType;

    @Column(name = "presentation_style")
    private String presentationStyle;

    @Column(name = "status")
    protected Integer status = 1;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workbook_id", nullable = false)
    private WorkbookEntity workbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataview_id", nullable = false)
    private WorkbookDataViewEntity dataView;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "presentation", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WorkbookPresentationPropertyEntity> properties = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentationType() {
        return presentationType;
    }

    public void setPresentationType(String presentationType) {
        this.presentationType = presentationType;
    }

    public String getPresentationStyle() {
        return presentationStyle;
    }

    public void setPresentationStyle(String presentationStyle) {
        this.presentationStyle = presentationStyle;
    }

    public WorkbookEntity getWorkbook() {
        return workbook;
    }

    public void setWorkbook(WorkbookEntity workbook) {
        this.workbook = workbook;
    }

    public WorkbookDataViewEntity getDataView() {
        return dataView;
    }

    public void setDataView(WorkbookDataViewEntity dataView) {
        this.dataView = dataView;
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

    public List<WorkbookPresentationPropertyEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<WorkbookPresentationPropertyEntity> properties) {
        this.properties = properties;
        this.properties.forEach(p -> p.setPresentation(this));
    }

    public void affProperty(WorkbookPresentationPropertyEntity property) {
        property.setPresentation(this);
        this.properties.add(property);
    }
}
