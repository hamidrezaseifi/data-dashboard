package de.mediqon.generic.data_dashboard.authenticatin.db;

import de.mediqon.generic.data_dashboard_common.credential.IPasswordHashGenerator;
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
import java.util.*;

@Singleton
@Requires(property = "mq-app-service.authentication", value = "db")
public class DbAuthenticator implements IAuthenticator {

    private final IUserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final IPasswordHashGenerator passwordHashGenerator;
    private final IUserAdapter userAdapter;
    private final ICustomerAdapter customerAdapter;


    public DbAuthenticator(IUserRepository userRepository,
                           ICustomerRepository customerRepository,
                           IPasswordHashGenerator passwordHashGenerator,
                           IUserAdapter userAdapter,
                           ICustomerAdapter customerAdapter) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordHashGenerator = passwordHashGenerator;
        this.userAdapter = userAdapter;
        this.customerAdapter = customerAdapter;
    }

    @Override
    public Optional<RefreshTokenUserModel> authenticate(String username,
                                                        String password) {
        if (username != null && password != null) {

            Optional<UserEntity> userOptional = userRepository.getByUsername(username);

            if (userOptional.isPresent()) {

                UserEntity userEntity = userOptional.get();
                Optional<CustomerEntity> customerEntityOptional =
                        customerRepository.getById(userEntity.getCustomerId());

                if (userEntity.isActive()
                    /*&& !authenticationAttemptMonitor.isAttemptProhibited(userEntity.getUsername())*/) {

                    UUID userId = userEntity.getId();
                    UUID customerId = userEntity.getCustomerId();

                    String passwordSalt = userEntity.getPasswordSalt();
                    String passwordHash = passwordHashGenerator.produceHash(password, passwordSalt);

                    boolean isCorrectUsername = username.equalsIgnoreCase(userEntity.getUsername());
                    boolean isCorrectPassword = passwordHash.equals(userEntity.getPasswordHash());

                    if (isCorrectUsername) {

                        if (isCorrectPassword) {

                            //userEntity.setAttempts(0);
                            //authenticationAttemptMonitor.notifyAttemptValid(userEntity.getUsername());

                            UserDto userDto = userAdapter.toDto(userEntity);
                            CustomerDto customerDto = customerAdapter.toDto(customerEntityOptional.get());

                            RefreshTokenUserModel refreshTokenUserModel =
                                    generateRefreshTokenUserModel(userDto, customerDto);

                            return Optional.of(refreshTokenUserModel);

                        } else {
                            return Optional.of(new RefreshTokenUserModel("ungültiger Benutzername oder Passwort!"));
                        }

                    } else {
                        return Optional.of(new RefreshTokenUserModel("ungültiger Benutzername oder Passwort!"));
                    }
                } else {
                    return Optional.of(new RefreshTokenUserModel("Benutzer ist deaktiviert!"));
                }
            }
        }
        return Optional.of(new RefreshTokenUserModel("ungültiger Benutzername oder Passwort!"));
    }

    private RefreshTokenUserModel generateRefreshTokenUserModel(UserDto user, CustomerDto customer) {

        RefreshTokenUserModel refreshTokenUserModel = new RefreshTokenUserModel(user, customer);

        refreshTokenUserModel.setRefreshToken("");

        return refreshTokenUserModel;
    }
}
