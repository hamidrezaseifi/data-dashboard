package de.mediqon.generic.data_dashboard.dataconnection.enums;

import de.mediqon.generic.data_dashboard.exceptions.EnumValueNotFoundException;

public enum EDatabaseType {
    MS_SQL_SERVER("sqlserver", 1),
    MYSQL("mysql", 2),
    POSTGRESQL("postgresql", 3),
    ORACLE("oracle:thin", 4),
    CSV_FILE("", 10);
    private final String jdbcPrt;
    private final int id;

    EDatabaseType(String jdbcPrt,
                  int id) {
        this.jdbcPrt = jdbcPrt;
        this.id = id;
    }

    public String getJdbcPrt() {
        return jdbcPrt;
    }

    public int getId() {
        return id;
    }

    public static EDatabaseType fromId(int id){
        for(EDatabaseType item: values()){
            if(item.id == id){
                return  item;
            }
        }
        throw new EnumValueNotFoundException("EDatabaseType", id);
    }
}
