package de.mediqon.generic.data_dashboard.authenticatin;

import de.mediqon.generic.data_dashboard.models.RefreshTokenUserModel;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.*;

import javax.inject.Singleton;
import java.util.*;

@Singleton
@Requires(notEnv = Environment.TEST)
public class AuthenticationValidator implements IAuthenticationValidator {

    private final IAuthenticator authenticator;

    public AuthenticationValidator(IAuthenticator authenticator) {
        super();

        this.authenticator = authenticator;
    }

    @Override
    public AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                                   AuthenticationRequest authenticationRequest) {

        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        if (username != null && password != null) {

            Optional<RefreshTokenUserModel> refreshTokenUserModelOptional =
                    this.authenticator.authenticate(username, password);

            if (refreshTokenUserModelOptional.isPresent()) {

                RefreshTokenUserModel refreshTokenUserModel = refreshTokenUserModelOptional.get();
                if(refreshTokenUserModel.hasError()){
                    return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
                }

                if (refreshTokenUserModel.getUser().isActive()) {

                    Date issued = new Date();

                    Map<String, Object> attr = new HashMap<>();
                    attr.put(ISSUED_ATTRIBUTE_KEY, issued);
                    attr.put(USERNAME_ATTRIBUTE_KEY, username.toLowerCase());
                    attr.put(REFRESH_TOKEN_MODEL_ATTRIBUTE_KEY, refreshTokenUserModel);

                    UserDetails userDetails =
                            new UserDetails(refreshTokenUserModel.getUser().getUsername(),
                                            refreshTokenUserModel.getUser().getRoles(),
                                            attr);

                    return userDetails;

                }
                else
                    return new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED);
            }
            else
                return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
        }

        return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
    }
}
