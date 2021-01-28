package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "statements")
public class StatementEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "is_distinct")
    protected Boolean isDistinct;

    @Column(name = "result_limit")
    private int resultLimit = -1;

    @Column(name = "status")
    protected Integer status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "statement", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<StatementSelectFieldEntity> selectFields = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "statement", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<StatementWhereFieldEntity> whereFields = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "statement", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<StatementSortingFieldEntity> sortingFields = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "statement", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<StatementHavingFieldEntity> havingFields = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "statement", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<StatementGroupByFieldEntity> groupByFields = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "connection_id", referencedColumnName = "id", nullable=false)
    private ConnectionPropertiesEntity connectionProperties;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getDistinct() {
        return isDistinct;
    }

    public void setDistinct(Boolean distinct) {
        isDistinct = distinct;
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

    public List<StatementSelectFieldEntity> getSelectFields() {
        return selectFields;
    }

    public void addSelectField(StatementSelectFieldEntity selectFieldEntity) {
        selectFieldEntity.setStatement(this);
        selectFields.add(selectFieldEntity);
    }


    public List<StatementWhereFieldEntity> getWhereFields() {
        return whereFields;
    }

    public List<StatementSortingFieldEntity> getSortingFields() {
        return sortingFields;
    }

    public List<StatementHavingFieldEntity> getHavingFields() {
        return havingFields;
    }

    public void addHavingField(StatementHavingFieldEntity havingFieldEntity) {
        havingFieldEntity.setStatement(this);
        havingFields.add(havingFieldEntity);
    }

    public List<StatementGroupByFieldEntity> getGroupByFields() {
        return groupByFields;
    }

    public void addGroupByField(StatementGroupByFieldEntity groupByFieldEntity) {
        groupByFieldEntity.setStatement(this);
        groupByFields.add(groupByFieldEntity);
    }

    public ConnectionPropertiesEntity getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(ConnectionPropertiesEntity connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public void setSelectFields(List<StatementSelectFieldEntity> selectFields) {
        this.selectFields = selectFields;
    }

    public void setWhereFields(List<StatementWhereFieldEntity> whereFields) {
        this.whereFields = whereFields;
    }

    public void setSortingFields(List<StatementSortingFieldEntity> sortingFields) {
        this.sortingFields = sortingFields;
    }

    public void setHavingFields(List<StatementHavingFieldEntity> havingFields) {
        this.havingFields = havingFields;
    }

    public void setGroupByFields(List<StatementGroupByFieldEntity> groupByFields) {
        this.groupByFields = groupByFields;
    }

    public boolean hasConnection() {
        return connectionProperties != null;
    }

    public int getResultLimit() {
        return resultLimit;
    }

    public void setResultLimit(int resultLimit) {
        this.resultLimit = resultLimit;
    }

    public StatementEntity() {
    }

}
