package de.mediqon.generic.data_dashboard.authenticatin;

import de.mediqon.generic.data_dashboard.models.RefreshTokenUserModel;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.config.RedirectConfiguration;
import io.micronaut.security.errors.PriorToLoginPersistence;
import io.micronaut.security.filters.SecurityFilter;
import io.micronaut.security.session.SessionLoginHandler;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;
import io.micronaut.session.http.SessionForRequest;

import java.util.Map;

@Replaces(SessionLoginHandler.class)
public class AppSessionLoginHandler extends SessionLoginHandler {

    private final String rolesKeyName;
    private final String nameKey;

    public AppSessionLoginHandler(RedirectConfiguration redirectConfiguration,
                                  SessionStore<Session> sessionStore,
                                  TokenConfiguration tokenConfiguration,
                                  @Nullable PriorToLoginPersistence priorToLoginPersistence) {
        super(redirectConfiguration, sessionStore, tokenConfiguration, priorToLoginPersistence);
        this.rolesKeyName = tokenConfiguration.getRolesName();
        this.nameKey = tokenConfiguration.getNameKey();


    }

    @Override
    public MutableHttpResponse<?> loginSuccess(UserDetails userDetails,
                                               HttpRequest<?> request) {
        Session session = (Session) SessionForRequest.find(request).orElseGet(() -> {
            return SessionForRequest.create(this.sessionStore, request);
        });
        session.put(SecurityFilter.AUTHENTICATION, new AuthenticationUserDetailsAdapter(userDetails, this.rolesKeyName, this.nameKey));

        Map<String, Object> map = userDetails.getAttributes("roles", "uname");
        map.put("sessionId", session.getId());
        RefreshTokenUserModel tokenUserModel =
                (RefreshTokenUserModel)map.get(IAuthenticationValidator.REFRESH_TOKEN_MODEL_ATTRIBUTE_KEY);
        session.put("user-data", tokenUserModel);

        //tokenUserModel.setDisplayName();
        //tokenUserModel.setSessionId(session.getId());
        //map.put("session-data", tokenUserModel);

        //String cookieValue = cookieHttpSessionIdGenerator.cookieValueFromSession(session);
        //map.put("session-cookie-data", cookieValue);
        //map.put("session-cookie-name", configuration.getCookieName());

        return HttpResponse.ok(tokenUserModel);
    }

    @Override
    public MutableHttpResponse<?> loginFailed(AuthenticationResponse authenticationFailed, HttpRequest<?> request) {
        return super.loginFailed(authenticationFailed, request);
    }
}
