package de.mediqon.generic.data_dashboard.models.dto.data.workbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookDataViewEntity;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({})
@Introspected
public class WorkbookDto extends BaseDto {

    private String name;

    private String description;

    protected Integer status = 1;

    private UUID customerId;

    private String customerName;

    private List<WorkbookDataSourceDto> dataSources = new ArrayList<>();

    private List<WorkbookDataViewDto> dataViews = new ArrayList<>();

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

    public List<WorkbookDataSourceDto> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<WorkbookDataSourceDto> dataSources) {
        this.dataSources = dataSources;
    }

    public void addDataSource(WorkbookDataSourceDto dataSource) {
        this.dataSources.add(dataSource);
    }

    public List<WorkbookDataViewDto> getDataViews() {
        return dataViews;
    }

    public void setDataViews(List<WorkbookDataViewDto> dataViews) {
        this.dataViews = dataViews;
    }

    public void addDataView(WorkbookDataViewDto dataView) {
        this.dataViews.add(dataView);
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
