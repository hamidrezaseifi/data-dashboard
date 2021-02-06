package de.mediqon.generic.data_dashboard.models.dto.data.workbook.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.mediqon.generic.data_dashboard.exceptions.EnumValueNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EWorkbookPresentationType {
    LABEL("Etikette", "label"),
    TABLE("Tabelle", "table"),
    PIE_CHART("Kuchendiagramm", "pie_chart"),
    BAR_CHART("Balkendiagramm", "bar_chart"),
    LINE_CHART("Liniendiagramm", "line_chart"),
    SLICES_CHART("SCHEIBENDiagramm", "slices_chart");

    private final String title;
    private final String id;

    EWorkbookPresentationType(String title,
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
    public static EWorkbookPresentationType fromId(String id){
        for(EWorkbookPresentationType presentationType: values()){
            if(presentationType.id.equals(id)){
                return presentationType;
            }
        }

        throw new EnumValueNotFoundException("EWorkbookPresentationType", id);
    }

    public static List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for(EWorkbookPresentationType presentationType: values()){
            Map<String, Object> map = new HashMap<>();
            map.put("title", presentationType.getTitle());
            map.put("id", presentationType.getId());
            map.put("value", presentationType.toString());
            list.add(map);
        }

        return list;
    }
}
