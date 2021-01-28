package de.mediqon.generic.data_dashboard.enums;

public enum EUserStatus {
  ACTIVE(1),
  DISABLED(10),
  LOCKED(20),
  DELETED(30);

  private final Integer id;

  private EUserStatus(final Integer id) {
    this.id = id;
  }

  public Integer getValue() {
    return this.id;
  }

}
