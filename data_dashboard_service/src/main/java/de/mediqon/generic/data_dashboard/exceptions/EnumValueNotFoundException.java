package de.mediqon.generic.data_dashboard.exceptions;

public class EnumValueNotFoundException extends RuntimeException {

    public EnumValueNotFoundException(String message) {
        super(message);
    }

    public EnumValueNotFoundException(String message,
                                      Throwable cause) {
        super(message, cause);
    }

    public EnumValueNotFoundException(String enumName, Object value){
        super(String.format("Enum %s für wert %d nicht gefunden!", enumName, value));
    }
}
