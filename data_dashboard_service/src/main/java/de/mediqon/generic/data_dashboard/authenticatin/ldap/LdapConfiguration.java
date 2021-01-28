package de.mediqon.generic.data_dashboard.authenticatin.ldap;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.inject.Singleton;

@Singleton
@ConfigurationProperties("mq-app-service.ldap")
public class LdapConfiguration {

    private String domainName;
    private String url;
    private String base;


    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
