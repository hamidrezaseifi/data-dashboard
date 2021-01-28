package de.mediqon.generic.data_dashboard.adapters.impl;

import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;
import de.mediqon.generic.data_dashboard.adapters.ICustomerAdapter;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.models.dto.CustomerDto;

import javax.inject.Singleton;

@Singleton
public class CustomerAdapter extends ModelDtoAdapterBase<CustomerEntity, CustomerDto> implements ICustomerAdapter {

    public CustomerAdapter() {

    }

    @Override
    public CustomerEntity fromDto(CustomerDto dto) {
        CustomerEntity model = new CustomerEntity();
        model.setCustomerKey(dto.getCustomerKey());
        model.setName(dto.getName());
        model.setStatus(dto.getStatus().getValue());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public CustomerDto toDto(CustomerEntity model) {
        CustomerDto dto = new CustomerDto();
        dto.setCustomerKey(model.getCustomerKey());
        dto.setName(model.getName());
        dto.setStatusInt(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
