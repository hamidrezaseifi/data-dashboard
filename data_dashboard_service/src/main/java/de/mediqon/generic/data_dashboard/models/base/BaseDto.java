package de.mediqon.generic.data_dashboard.models.base;

import java.util.UUID;

public abstract class BaseDto {

    protected UUID id;

    protected Integer version;

    public BaseDto() {
        super();

        this.id = UUID.randomUUID();
        this.version = 0;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

}