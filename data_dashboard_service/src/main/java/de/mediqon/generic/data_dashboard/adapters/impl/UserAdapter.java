package de.mediqon.generic.data_dashboard.adapters.impl;

import de.mediqon.generic.data_dashboard_common.adapters.ModelDtoAdapterBase;
import de.mediqon.generic.data_dashboard.adapters.IUserAdapter;
import de.mediqon.generic.data_dashboard.entities.UserEntity;
import de.mediqon.generic.data_dashboard.models.dto.UserDto;

import javax.inject.Singleton;

@Singleton
public class UserAdapter extends ModelDtoAdapterBase<UserEntity, UserDto> implements IUserAdapter {

    public UserAdapter() {

    }

    @Override
    public UserEntity fromDto(UserDto dto) {
        UserEntity model = new UserEntity();
        model.setBirthDate(dto.getBirthDate());
        model.setEmail(dto.getEmail());
        model.setFirstName(dto.getFirstName());
        model.setCustomerId(dto.getCustomerId());
        model.setLastName(dto.getLastName());
        model.setPermission(dto.getPermission());
        model.setUsername(dto.getUsername());
        model.setRoles(dto.getRoles());
        model.setStatus(dto.getStatus());
        model.setVersion(dto.getVersion());
        model.setId(dto.getId());

        return model;
    }

    @Override
    public UserDto toDto(UserEntity model) {
        UserDto dto = new UserDto();

        dto.setBirthDate(model.getBirthDate());
        dto.setEmail(model.getEmail());
        dto.setFirstName(model.getFirstName());
        dto.setCustomerId(model.getCustomerId());
        dto.setLastName(model.getLastName());
        dto.setPermission(model.getPermission());
        dto.setUsername(model.getUsername());
        dto.setRoles(model.getRoles());
        dto.setStatus(model.getStatus());
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());

        return dto;
    }
}
