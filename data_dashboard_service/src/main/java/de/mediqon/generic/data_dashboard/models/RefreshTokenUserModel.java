//tag::clazzwithoutsettersandgetters[]
package de.mediqon.generic.data_dashboard.models;

import de.mediqon.generic.data_dashboard.authenticatin.IAuthenticationValidator;
import de.mediqon.generic.data_dashboard.models.dto.CustomerDto;
import de.mediqon.generic.data_dashboard.models.dto.UserDto;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import org.apache.commons.lang3.StringUtils;

public class RefreshTokenUserModel {

    private UserDto user;

    private CustomerDto customer;

    private String refreshToken;

    private String error;

    public RefreshTokenUserModel() {
        this.error = "";
    }

    public RefreshTokenUserModel(UserDto user,
                                 CustomerDto customer) {
        this();
        this.user = user;
        this.customer = customer;
    }

    public RefreshTokenUserModel(String error) {
        this.error = error;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hasError(){
        return StringUtils.isNotEmpty(error);
    }

    public static
        RefreshTokenUserModel generateRefreshTokenUserModelFromAuthentication(Authentication authentication){
        if(authentication instanceof AuthenticationUserDetailsAdapter){
            AuthenticationUserDetailsAdapter authenticationUserDetailsAdapter =
                    (AuthenticationUserDetailsAdapter)authentication;
            Object o =
                    authenticationUserDetailsAdapter.getAttributes().
                            get(IAuthenticationValidator.REFRESH_TOKEN_MODEL_ATTRIBUTE_KEY);
            RefreshTokenUserModel refreshTokenUserModel = (RefreshTokenUserModel)o;
            return refreshTokenUserModel;
        }

        return null;
    }

}
