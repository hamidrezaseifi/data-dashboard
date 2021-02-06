package de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.mediqon.generic.data_dashboard.exceptions.EnumValueNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EWorkbookDataSourceType {
    DATAVIEW("Datenansicht", "dataview"),
    FILTER("Filter", "filter");

    private final String title;
    private final String id;

    EWorkbookDataSourceType(String title,
                            String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    @JsonCreator
    public static EWorkbookDataSourceType fromId(String id){
        for(EWorkbookDataSourceType presentationType: values()){
            if(presentationType.id.equals(id)){
                return presentationType;
            }
        }

        throw new EnumValueNotFoundException("EWorkbookDataSourceType", id);
    }

    public static List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for(EWorkbookDataSourceType presentationType: values()){
            Map<String, Object> map = new HashMap<>();
            map.put("title", presentationType.getTitle());
            map.put("id", presentationType.getId());
            map.put("value", presentationType.toString());
            list.add(map);
        }

        return list;
    }
}
