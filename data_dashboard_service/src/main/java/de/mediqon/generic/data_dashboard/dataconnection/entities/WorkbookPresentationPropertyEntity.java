package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workbook_presentation_properties")
public class WorkbookPresentationPropertyEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", nullable = false)
    private WorkbookPresentationEntity presentation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public WorkbookPresentationEntity getPresentation() {
        return presentation;
    }

    public void setPresentation(WorkbookPresentationEntity presentation) {
        this.presentation = presentation;
    }
}
