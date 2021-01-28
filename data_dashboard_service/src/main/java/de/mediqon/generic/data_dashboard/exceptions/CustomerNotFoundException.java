package de.mediqon.generic.data_dashboard.exceptions;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customerKey) {
        super(String.format("Kunde für Schlüssel %s nicht gefunden!", customerKey));
    }

    public CustomerNotFoundException(UUID customerId) {
        super(String.format("Kunde für ID %s nicht gefunden!", customerId));
    }

}
