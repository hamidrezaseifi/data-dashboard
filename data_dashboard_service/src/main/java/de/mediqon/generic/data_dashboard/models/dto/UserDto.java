package de.mediqon.generic.data_dashboard.models.dto;

import de.mediqon.generic.data_dashboard.enums.EUserStatus;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;


import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UserDto extends BaseDto {

    private UUID customerId;

    private String username;

    private String email;

    private Date birthDate;

    private String firstName;

    private String lastName;

    private Integer permission;

    protected Integer status;

    private Set<String> roles;


    public UserDto() {
        super();
    }

    public UserDto(String username) {
        this();

        this.username = username;

    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isLocked(){
        return this.status == EUserStatus.LOCKED.getValue();
    }

    public boolean isActive(){
        return this.status == EUserStatus.ACTIVE.getValue();
    }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return lastName + ", " + firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}