package de.mediqon.generic.data_dashboard.models;

import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.ArrayList;
import java.util.List;

public class AppAccessRefreshToken extends BearerAccessRefreshToken {

    private String displayName;

    private String fullName;

    private String email;

    private String lastName;

    private String firstName;

    private String homeDirectory;

    private String userPrincipalName;

    private List<String> groups = new ArrayList<>();

    public AppAccessRefreshToken() {

    }

    public void setUserDetails(UserDetails userDetails) {

    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
