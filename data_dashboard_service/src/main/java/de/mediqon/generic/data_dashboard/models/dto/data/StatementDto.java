package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.*;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class StatementDto extends BaseDto {

    private String name;

    private String tableName;

    protected Boolean isDistinct;

    protected Integer status;

    private int resultLimit = -1;
    
    private List<StatementSelectFieldDto> selectFields = new ArrayList<>();

    private List<StatementWhereFieldDto> whereFields = new ArrayList<>();

    private List<StatementSortingFieldDto> sortingFields = new ArrayList<>();

    private List<StatementHavingFieldDto> havingFields = new ArrayList<>();

    private List<StatementGroupByFieldDto> groupByFields = new ArrayList<>();

    private ConnectionPropertiesDto connectionProperties;


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

    public List<StatementSelectFieldDto> getSelectFields() {
        return selectFields;
    }

    public void addSelectField(StatementSelectFieldDto selectFieldEntity) {
        selectFields.add(selectFieldEntity);
    }


    public List<StatementWhereFieldDto> getWhereFields() {
        return whereFields;
    }

    public List<StatementSortingFieldDto> getSortingFields() {
        return sortingFields;
    }

    public List<StatementHavingFieldDto> getHavingFields() {
        return havingFields;
    }

    public void addHavingField(StatementHavingFieldDto havingFieldEntity) {
        havingFields.add(havingFieldEntity);
    }

    public List<StatementGroupByFieldDto> getGroupByFields() {
        return groupByFields;
    }

    public void addGroupByField(StatementGroupByFieldDto groupByFieldEntity) {
        groupByFields.add(groupByFieldEntity);
    }

    public void setSelectFields(List<StatementSelectFieldDto> selectFields) {
        this.selectFields = selectFields;
    }

    public void setWhereFields(List<StatementWhereFieldDto> whereFields) {
        this.whereFields = whereFields;
    }

    public void setSortingFields(List<StatementSortingFieldDto> sortingFields) {
        this.sortingFields = sortingFields;
    }

    public void setHavingFields(List<StatementHavingFieldDto> havingFields) {
        this.havingFields = havingFields;
    }

    public void setGroupByFields(List<StatementGroupByFieldDto> groupByFields) {
        this.groupByFields = groupByFields;
    }

    public ConnectionPropertiesDto getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(ConnectionPropertiesDto connectionProperties) {
        this.connectionProperties = connectionProperties;
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

    public StatementDto() {
    }

}
