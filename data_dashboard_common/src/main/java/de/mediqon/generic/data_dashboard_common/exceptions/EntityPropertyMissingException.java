package de.mediqon.generic.data_dashboard_common.exceptions;

public class EntityPropertyMissingException extends RuntimeException {

    public EntityPropertyMissingException(String message) {
        super(message);
    }

    public EntityPropertyMissingException(String message,
                                          Throwable cause) {
        super(message, cause);
    }
}
