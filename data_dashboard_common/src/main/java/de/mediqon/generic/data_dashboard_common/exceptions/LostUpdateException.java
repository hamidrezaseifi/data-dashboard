package de.mediqon.generic.data_dashboard_common.exceptions;

public class LostUpdateException extends RuntimeException {

    public LostUpdateException(String message) {
        super(message);
    }

    public LostUpdateException(String message,
                               Throwable cause) {
        super(message, cause);
    }
}
