package de.mediqon.generic.data_dashboard.models.dto.data.workbook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class WorkbookDataSourceDto extends BaseDto {

    private UUID connectionId;

    private String name;

    private String dataSourceType;

    private String table;

    private String query;

    protected Integer status = 1;

    private List<WorkbookDataSourceColumnDto> columns = new ArrayList<>();

    public UUID getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(UUID connectionId) {
        this.connectionId = connectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<WorkbookDataSourceColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<WorkbookDataSourceColumnDto> columns) {
        this.columns = columns;
    }

    public void addColumn(WorkbookDataSourceColumnDto column) {
        this.columns.add(column);
    }
}
