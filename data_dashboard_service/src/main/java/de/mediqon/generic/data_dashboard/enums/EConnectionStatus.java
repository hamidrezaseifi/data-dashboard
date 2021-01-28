package de.mediqon.generic.data_dashboard.enums;

import de.mediqon.generic.data_dashboard.exceptions.EnumValueNotFoundException;

public enum EConnectionStatus {
    ACTIVE(1),
    SUSPEND(10),
    DELETED(20);

    private int value;

    EConnectionStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EConnectionStatus fromValue(int value){
        for(EConnectionStatus item: values()){
            if(item.getValue() == value){
                return item;
            }
        }
        throw new EnumValueNotFoundException("ECustomerStatus", value);
    }
}
