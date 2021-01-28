package de.mediqon.generic.data_dashboard.services;

import io.micronaut.websocket.WebSocketSession;

import java.util.UUID;

public interface ISocketTaskUserMessageManager {

    void openSocket(WebSocketSession session,
                    UUID taskId,
                    String username);

    void setSocket(WebSocketSession session,
                   UUID taskId,
                   String username);

    void closeSocket(WebSocketSession session,
                     UUID taskId,
                     String username);

    void sendMessage(UUID taskId,
                     String username,
                     Object message);

}
