package de.mediqon.generic.data_dashboard.authenticatin;

import de.mediqon.generic.data_dashboard.models.RefreshTokenUserModel;

import java.util.Optional;

public interface IAuthenticator {

    Optional<RefreshTokenUserModel> authenticate(String username,
                                                 String password);
}
