package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({})
@Introspected
public class ColumnDetails {

    private String columnName;
    private String columnType;
    private int size;

    public ColumnDetails() {
    }

    public ColumnDetails(String name,
                         String type,
                         int size) {
        this.columnName = name;
        this.columnType = type;
        this.size = size;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
