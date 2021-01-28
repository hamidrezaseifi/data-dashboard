package de.mediqon.generic.data_dashboard_common.credential;

public interface IPasswordHashGenerator {

    String produceSalt();

    String produceHash(String password,
                       String passwordSalt);
}
