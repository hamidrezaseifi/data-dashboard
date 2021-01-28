package de.mediqon.generic.data_dashboard.authenticatin;

import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;

public interface IAuthenticationValidator {

    String REFRESH_TOKEN_MODEL_ATTRIBUTE_KEY = "ue";
    String CUSTOMER_ATTRIBUTE_KEY = "ue";
   // String EMAIL_ATTRIBUTE_KEY = "email";
    //String DISPLAY_NAME_ATTRIBUTE_KEY = "dn";
    String USERNAME_ATTRIBUTE_KEY = "uname";
    //String USER_PRINCIPAL_NAME_ATTRIBUTE_KEY = "uid";
    String ISSUED_ATTRIBUTE_KEY = "issued";

    AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                            AuthenticationRequest authenticationRequest);
}
