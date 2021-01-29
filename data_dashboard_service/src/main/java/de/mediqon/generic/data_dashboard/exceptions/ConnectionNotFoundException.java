package de.mediqon.generic.data_dashboard.exceptions;

import java.util.UUID;

public class ConnectionNotFoundException extends RuntimeException {

    public ConnectionNotFoundException(UUID id) {
        super(String.format("Verbindung für ID %s nicht gefunden!", id));
    }

}
