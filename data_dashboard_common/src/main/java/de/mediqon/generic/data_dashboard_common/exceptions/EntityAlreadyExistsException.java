package de.mediqon.generic.data_dashboard_common.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String message,
                                        Throwable cause) {
        super(message, cause);
    }
}
