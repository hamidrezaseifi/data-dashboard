package de.mediqon.generic.data_dashboard.authenticatin.ldap;

import de.mediqon.generic.data_dashboard.adapters.ICustomerAdapter;
import de.mediqon.generic.data_dashboard.adapters.IUserAdapter;
import de.mediqon.generic.data_dashboard.authenticatin.IAuthenticator;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.entities.UserEntity;
import de.mediqon.generic.data_dashboard.models.RefreshTokenUserModel;
import de.mediqon.generic.data_dashboard.models.dto.CustomerDto;
import de.mediqon.generic.data_dashboard.models.dto.UserDto;
import de.mediqon.generic.data_dashboard.repositories.ICustomerRepository;
import de.mediqon.generic.data_dashboard.repositories.IUserRepository;
import io.micronaut.context.annotation.Requires;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.Optional;

@Singleton
@Requires(property = "mq-app-service.authentication", value = "ldap")
public class LdapAuthenticator implements IAuthenticator {

    private final LdapConfiguration ldapConfiguration;
    private final IUserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final IUserAdapter userAdapter;
    private final ICustomerAdapter customerAdapter;

    public LdapAuthenticator(LdapConfiguration ldapConfiguration,
                             IUserRepository userRepository,
                             ICustomerRepository customerRepository,
                             IUserAdapter userAdapter,
                             ICustomerAdapter customerAdapter) {
        this.ldapConfiguration = ldapConfiguration;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.userAdapter = userAdapter;
        this.customerAdapter = customerAdapter;
    }

    @Override
    public Optional<RefreshTokenUserModel> authenticate(String username, String password) {

        if(username.contains("@") == false){
            username = username + "@" + this.ldapConfiguration.getDomainName();
        }

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        env.put(Context.SECURITY_PRINCIPAL, username);

        env.put(Context.SECURITY_CREDENTIALS, password);

        env.put(Context.PROVIDER_URL, this.ldapConfiguration.getUrl());

        try {

            DirContext context = new InitialDirContext(env);
            System.out.println("Connection Successful.");

            SearchControls ctrls = new SearchControls();
            ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchPhrase = "(& (userPrincipalName=" + username + ")(objectClass=user))";
            NamingEnumeration<SearchResult> results = context.search(toDC(this.ldapConfiguration.getDomainName()),
                                                                     searchPhrase,
                                                                     ctrls);
            if(!results.hasMore()){
                return Optional.of(new RefreshTokenUserModel("Principal name not found"));
                //throw new AuthenticationException("Principal name not found");
            }

            //SearchResult result = results.next();
            //System.out.println("distinguisedName: " + result.getNameInNamespace() ); // CN=Firstname Lastname,OU=Mycity,DC=mydomain,DC=com
            //RefreshTokenUserModel refreshTokenUserModel = generateRefreshTokenUserModel(result, context, password);

            context.close();

            Optional<UserEntity> userOptional = userRepository.getByUsername(username);
            Optional<CustomerEntity> customerOptional =
                    customerRepository.getById(userOptional.get().getCustomerId());

            UserDto userDto = userAdapter.toDto(userOptional.get());
            CustomerDto customerDto = customerAdapter.toDto(customerOptional.get());

            RefreshTokenUserModel refreshTokenUserModel = new RefreshTokenUserModel(userDto, customerDto);

            return Optional.of(refreshTokenUserModel);

        } catch (Exception e) {
            //throw new AuthenticationException("Principal name not found");
            return Optional.of(new RefreshTokenUserModel("Error in authentication: " + e.getLocalizedMessage()));
        }

    }

    private String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        for (String token : domainName.split("\\.")) {
            if(token.length()==0) continue;
            if(buf.length()>0)  buf.append(",");
            buf.append("DC=").append(token);
        }
        return buf.toString();
    }

    private RefreshTokenUserModel generateRefreshTokenUserModel(SearchResult result,
                                                                DirContext context,
                                                                String password) throws NamingException {

        RefreshTokenUserModel refreshTokenUserModel = new RefreshTokenUserModel();

        /*refreshTokenUserModel.setDisplayName(result.getAttributes().get("displayname").get().toString());
        refreshTokenUserModel.setEmail(result.getAttributes().get("mail").get().toString());
        refreshTokenUserModel.setFirstName(result.getAttributes().get("givenname").get().toString());
        refreshTokenUserModel.setFullName(result.getAttributes().get("name").get().toString());
        refreshTokenUserModel.setHomeDirectory(result.getAttributes().get("homedirectory").get().toString());
        refreshTokenUserModel.setLastName(result.getAttributes().get("sn").get().toString());
        refreshTokenUserModel.setUsername(result.getAttributes().get("userprincipalname").get().toString());
        refreshTokenUserModel.setUserPrincipalName(result.getAttributes().get("userprincipalname").get().toString());

        Attribute memberOf = result.getAttributes().get("memberOf");
        if(memberOf!=null) {
            for(int idx=0; idx<memberOf.size(); idx++) {
                Attribute att = context.getAttributes(memberOf.get(idx).toString(), new String[]{"CN"}).get("CN");
                refreshTokenUserModel.addGroup(att.get().toString());
            }
        }*/

        return refreshTokenUserModel;
    }
}
