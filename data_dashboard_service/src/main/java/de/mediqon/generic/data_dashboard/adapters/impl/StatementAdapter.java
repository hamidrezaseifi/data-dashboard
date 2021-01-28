package de.mediqon.generic.data_dashboard.adapters.impl;

import de.mediqon.generic.data_dashboard.adapters.IConnectionPropertiesAdapter;
import de.mediqon.generic.data_dashboard.adapters.IStatementAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.*;
import de.mediqon.generic.data_dashboard.models.dto.data.*;
import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;

import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class StatementAdapter extends ModelDtoAdapterBase<StatementEntity, StatementDto>
        implements IStatementAdapter {

    private final IConnectionPropertiesAdapter connectionPropertiesAdapter;

    public StatementAdapter(IConnectionPropertiesAdapter connectionPropertiesAdapter) {
        this.connectionPropertiesAdapter = connectionPropertiesAdapter;
    }

    @Override
    public StatementEntity fromDto(StatementDto dto) {
        StatementEntity model = new StatementEntity();
        model.setTableName(dto.getTableName());
        model.setDistinct(dto.getDistinct());
        model.setResultLimit(dto.getResultLimit());
        model.setName(dto.getName());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());
        model.setConnectionProperties(connectionPropertiesAdapter.fromDto(dto.getConnectionProperties()));

        model.setSelectFields(dto.getSelectFields().stream().map(f -> fromDto(f)).collect(Collectors.toList()));
        model.setGroupByFields(dto.getGroupByFields().stream().map(f -> fromDto(f)).collect(Collectors.toList()));
        model.setHavingFields(dto.getHavingFields().stream().map(f -> fromDto(f)).collect(Collectors.toList()));
        model.setSortingFields(dto.getSortingFields().stream().map(f -> fromDto(f)).collect(Collectors.toList()));
        model.setWhereFields(dto.getWhereFields().stream().map(f -> fromDto(f)).collect(Collectors.toList()));

        return model;
    }

    @Override
    public StatementDto toDto(StatementEntity model) {
        StatementDto dto = new StatementDto();
        dto.setTableName(model.getTableName());
        dto.setDistinct(model.getDistinct());
        dto.setResultLimit(model.getResultLimit());
        dto.setName(model.getName());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setConnectionProperties(connectionPropertiesAdapter.toDto(model.getConnectionProperties()));


        dto.setSelectFields(model.getSelectFields().stream().map(f -> toDto(f)).collect(Collectors.toList()));
        dto.setGroupByFields(model.getGroupByFields().stream().map(f -> toDto(f)).collect(Collectors.toList()));
        dto.setHavingFields(model.getHavingFields().stream().map(f -> toDto(f)).collect(Collectors.toList()));
        dto.setSortingFields(model.getSortingFields().stream().map(f -> toDto(f)).collect(Collectors.toList()));
        dto.setWhereFields(model.getWhereFields().stream().map(f -> toDto(f)).collect(Collectors.toList()));

        return dto;
    }

    private StatementSelectFieldEntity fromDto(StatementSelectFieldDto dto){
        StatementSelectFieldEntity model = new StatementSelectFieldEntity();
        model.setFieldLabel(dto.getFieldLabel());
        model.setFieldName(dto.getFieldName());
        model.setFieldTypeEnum(dto.getFieldType());
        model.setStatus(dto.getStatus());
        model.setId(dto.getId());
        model.setVersion(dto.getVersion());

        return model;
    }

    private StatementSelectFieldDto toDto(StatementSelectFieldEntity model){
        StatementSelectFieldDto dto = new StatementSelectFieldDto();
        dto.setFieldLabel(model.getFieldLabel());
        dto.setFieldName(model.getFieldName());
        dto.setFieldTypeInt(model.getFieldType());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());
        dto.setVersion(model.getVersion());

        return dto;
    }

    private StatementGroupByFieldEntity fromDto(StatementGroupByFieldDto dto){
        StatementGroupByFieldEntity model = new StatementGroupByFieldEntity();
        model.setFieldName(dto.getFieldName());
        model.setStatus(dto.getStatus());
        model.setId(dto.getId());
        model.setVersion(dto.getVersion());

        return model;
    }

    private StatementGroupByFieldDto toDto(StatementGroupByFieldEntity model){
        StatementGroupByFieldDto dto = new StatementGroupByFieldDto();
        dto.setFieldName(model.getFieldName());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());
        dto.setVersion(model.getVersion());

        return dto;
    }

    private StatementHavingFieldEntity fromDto(StatementHavingFieldDto dto){
        StatementHavingFieldEntity model = new StatementHavingFieldEntity();
        model.setFieldName(dto.getFieldName());
        model.setFieldType(dto.getFieldType().getId());
        model.setStatus(dto.getStatus());
        model.setFieldValue(dto.getFieldValue());
        model.setId(dto.getId());
        model.setVersion(dto.getVersion());

        return model;
    }

    private StatementHavingFieldDto toDto(StatementHavingFieldEntity model){
        StatementHavingFieldDto dto = new StatementHavingFieldDto();
        dto.setFieldValue(model.getFieldValue());
        dto.setFieldName(model.getFieldName());
        dto.setFieldTypeInt(model.getFieldType());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());
        dto.setVersion(model.getVersion());

        return dto;
    }

    private StatementSortingFieldEntity fromDto(StatementSortingFieldDto dto){
        StatementSortingFieldEntity model = new StatementSortingFieldEntity();
        model.setFieldName(dto.getFieldName());
        model.setSortType(dto.getSortType());
        model.setStatus(dto.getStatus());
        model.setId(dto.getId());
        model.setVersion(dto.getVersion());

        return model;
    }

    private StatementSortingFieldDto toDto(StatementSortingFieldEntity model){
        StatementSortingFieldDto dto = new StatementSortingFieldDto();
        dto.setSortType(model.getSortType());
        dto.setFieldName(model.getFieldName());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());
        dto.setVersion(model.getVersion());

        return dto;
    }


    private StatementWhereFieldEntity fromDto(StatementWhereFieldDto dto){
        StatementWhereFieldEntity model = new StatementWhereFieldEntity();
        model.setFieldValue(dto.getFieldValue());
        model.setFieldName(dto.getFieldName());
        model.setFieldType(dto.getFieldType().getId());
        model.setStatus(dto.getStatus());
        model.setId(dto.getId());
        model.setVersion(dto.getVersion());

        return model;
    }

    private StatementWhereFieldDto toDto(StatementWhereFieldEntity model){
        StatementWhereFieldDto dto = new StatementWhereFieldDto();
        dto.setFieldValue(model.getFieldValue());
        dto.setFieldName(model.getFieldName());
        dto.setFieldTypeInt(model.getFieldType());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());
        dto.setVersion(model.getVersion());

        return dto;
    }
}
