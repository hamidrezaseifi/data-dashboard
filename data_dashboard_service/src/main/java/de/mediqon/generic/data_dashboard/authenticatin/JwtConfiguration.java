package de.mediqon.generic.data_dashboard.authenticatin;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.inject.Singleton;

@Singleton
@ConfigurationProperties("micronaut.security.token.jwt.generator")
public class JwtConfiguration {

    private int accessTokenExpiration;
    private int refreshTokenExpiration;


    public int getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public void setAccessTokenExpiration(int accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public int getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(int refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}
