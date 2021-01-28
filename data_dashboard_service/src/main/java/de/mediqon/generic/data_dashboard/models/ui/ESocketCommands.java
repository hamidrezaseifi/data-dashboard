package de.mediqon.generic.data_dashboard.models.ui;

public enum ESocketCommands {

  STATUS("status"),
  COMMAND("command"),
  ERROR_MESSAGE("errorMessage"),
  ERROR_DETAIL("errorDetail"),
  FILE_NAME("fileName"),
  FILE_HASH("fileHash"),
  USERNAME("username"),
  USER_LOGGED_IN("user_logen_in"),
  TOKEN("token"),
  TASK_ID("task_id"),
  STATUS_CHANGED("status_change"),

  TASK_RELOAD("message-reload");

  private final String value;

  ESocketCommands(final String value) {

    this.value = value;
  }

  public String getValue() {

    return this.value;
  }

}
