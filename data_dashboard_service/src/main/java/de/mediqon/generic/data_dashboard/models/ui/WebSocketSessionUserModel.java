package de.mediqon.generic.data_dashboard.models.ui;

import io.micronaut.websocket.WebSocketSession;

import java.util.UUID;

public class WebSocketSessionUserModel {
    private WebSocketSession socketSession;
    private UUID taskId;
    private String username;

    public WebSocketSessionUserModel(WebSocketSession socketSession,
                                     UUID taskId,
                                     String username) {
        this.socketSession = socketSession;
        this.taskId = taskId;
        this.username = username;
    }

    public WebSocketSession getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(WebSocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isClosed(){
        return this.socketSession.isOpen() == false;
    }

    public void sendMessageAsync(Object message){
        socketSession.sendAsync(message);
    }
}
