package de.mediqon.generic.data_dashboard.authenticatin;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@Singleton
@Requires(notEnv = Environment.TEST)
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final IAuthenticationValidator authenticationValidator;

    public AuthenticationProviderUserPassword(IAuthenticationValidator authenticationValidator) {
        super();

        this.authenticationValidator = authenticationValidator;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        AuthenticationResponse authenticationResponse =
                authenticationValidator.evaluate(authenticationRequest);

        return Flowable.just(authenticationResponse);
    }
}
