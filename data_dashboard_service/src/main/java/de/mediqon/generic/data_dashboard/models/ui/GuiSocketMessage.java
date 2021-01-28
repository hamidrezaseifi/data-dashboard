package de.mediqon.generic.data_dashboard.models.ui;


import de.mediqon.generic.data_dashboard_common.exception.ExceptionHelper;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class GuiSocketMessage extends HashMap<String, Object> {

  private static final long serialVersionUID = 3425395554514027407L;

  public GuiSocketMessage() {

  }

  public GuiSocketMessage(final int initialCapacity) {

    super(initialCapacity);
  }

  public GuiSocketMessage(final Map<? extends String, ? extends String> m) {

    super(m);

  }

  public GuiSocketMessage(final int initialCapacity, final float loadFactor) {

    super(initialCapacity,loadFactor);
  }

  public boolean hasFileHash() {

    return this.containsKey(ESocketCommands.FILE_HASH.getValue());
  }

  public String getFileNotHash() {

    final String encodedString = this.get(ESocketCommands.FILE_HASH.getValue()).toString();

    final String decodedString = GuiSocketMessage.decodeHashPath(encodedString);

    return decodedString;

  }

  public void setFileNotHash(final String filepath) {

    final String encodedString = GuiSocketMessage.encodeHashPath(filepath);

    this.put(ESocketCommands.FILE_HASH.getValue(), encodedString);
  }


  public String getStatus() {

    return this.get(ESocketCommands.STATUS.getValue()).toString();
  }

  public void setStatus(final String status) {

    this.put(ESocketCommands.STATUS.getValue(), status);
  }

  public String getFileName() {

    return this.get(ESocketCommands.FILE_NAME.getValue()).toString();
  }

  public void setFileName(final String status) {

    this.put(ESocketCommands.FILE_NAME.getValue(), status);
  }

  public void setUserLoggedIn(final String msg) {

    this.put(ESocketCommands.USER_LOGGED_IN.getValue(), msg);
  }

  public String getCommand() {

    return this.get(ESocketCommands.COMMAND.getValue()).toString();
  }

  public void setCommand(final String command) {

    this.put(ESocketCommands.COMMAND.getValue(), command);
  }

  public String getUsername() {

    return this.get(ESocketCommands.USERNAME.getValue()).toString();
  }

  public boolean hasUsername() {

    return this.containsKey(ESocketCommands.USERNAME.getValue());
  }

  public void setUsername(final String value) {

    this.put(ESocketCommands.USERNAME.getValue(), value);
  }

  public String getErrorMessage() {

    return this.get(ESocketCommands.ERROR_MESSAGE.getValue()).toString();
  }

  public void setErrorMessage(final String error) {

    this.put(ESocketCommands.ERROR_MESSAGE.getValue(), error);
  }

  public String getErrorDetail() {

    return this.get(ESocketCommands.ERROR_DETAIL.getValue()).toString();
  }

  public void setErrorDetail(final String error) {

    this.put(ESocketCommands.ERROR_DETAIL.getValue(), error);
  }

  public void setErrorDetail(final StackTraceElement[] stackTraces) {

    this.put(ESocketCommands.ERROR_DETAIL.getValue(), ExceptionHelper.stackListToString(stackTraces));
  }

  public boolean getTaskReload() {

    return "true".equals(this.get(ESocketCommands.TASK_RELOAD.getValue()).toString());
  }

  public void setTaskReload(final boolean value) {

    this.put(ESocketCommands.TASK_RELOAD.getValue(), value);
  }


  public String getTaskId() {
    return this.get(ESocketCommands.TASK_ID.getValue()).toString();
  }

  public boolean hasTaskId() {

    return this.containsKey(ESocketCommands.TASK_ID.getValue());
  }

  public void setTaskId(String token) {
    this.put(ESocketCommands.TASK_ID.getValue(), token);
  }




  public String getStatusChanged() {
    return this.get(ESocketCommands.STATUS_CHANGED.getValue()).toString();
  }

  public boolean hasStatusChanged() {

    return this.containsKey(ESocketCommands.STATUS_CHANGED.getValue());
  }

  public void setStatusChanged(String string) {
    this.put(ESocketCommands.STATUS_CHANGED.getValue(), string);
  }


  public String getToken() {
    return this.get(ESocketCommands.TOKEN.getValue()).toString();
  }

  public boolean hasToken() {

    return this.containsKey(ESocketCommands.TOKEN.getValue());
  }

  public void setToken(String token) {
    this.put(ESocketCommands.TOKEN.getValue(), token);
  }

  public static GuiSocketMessage generate(final String status) {

    final GuiSocketMessage message = new GuiSocketMessage();
    message.put(ESocketCommands.STATUS.getValue(), status);
    return message;
  }

  public static String decodeHashPath(final String encodedString) {

    final String decodedString = new String(Base64.getDecoder().decode(encodedString));

    return decodedString;
  }

  public static String encodeHashPath(final String filepath) {

    final String encodedString = Base64.getEncoder().encodeToString(filepath.getBytes());

    return encodedString;
  }

  @Override
  public GuiSocketMessage clone() {

    final GuiSocketMessage message = new GuiSocketMessage();
    for (final String key : this.keySet()) {
      message.put(key, this.get(key));
    }

    return message;

  }


}
