package de.mediqon.generic.data_dashboard.dataconnection.enums;

import de.mediqon.generic.data_dashboard.exceptions.EnumValueNotFoundException;

public enum EFieldType {
    VARCHAR(1),
    INT(10),
    FLOAT(15),
    DATE(20),
    TIMESTAMP(25),
    BOOLEAN(30);

    private final int id;

    EFieldType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isString(){
        return id < 10;
    }

    public boolean isNumber(){
        return id < 20 & id>=10;
    }

    public boolean isBoolean(){
        return this == BOOLEAN;
    }

    public static EFieldType fromId(int id){
        for(EFieldType type: values()){
            if(type.id == id){
                return type;
            }
        }

        throw new EnumValueNotFoundException("FieldType", id);
    }
}
