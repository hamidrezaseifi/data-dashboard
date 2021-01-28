package de.mediqon.generic.data_dashboard.controllers;

import de.mediqon.generic.data_dashboard.models.ui.GuiSocketMessage;
import de.mediqon.generic.data_dashboard.services.ISocketTaskUserMessageManager;
import io.micronaut.http.MediaType;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Secured(SecurityRule.IS_ANONYMOUS)
@ServerWebSocket("/websocket/task/{username}/{taskId}")
public class TaskMessageSocketController {

    private final WebSocketBroadcaster broadcaster;
    private final ISocketTaskUserMessageManager socketUserManager;

    public TaskMessageSocketController(WebSocketBroadcaster broadcaster,
                                       ISocketTaskUserMessageManager socketUserManager) {
        this.broadcaster = broadcaster;
        this.socketUserManager = socketUserManager;
    }

    @OnOpen
    public Publisher<GuiSocketMessage> onOpen(UUID taskId,
                                              String username,
                                              WebSocketSession session) {
        socketUserManager.openSocket(session, taskId, username);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-in");
        generatedMessage.setUserLoggedIn(username);

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnMessage
    public Publisher<GuiSocketMessage> onMessage(
            UUID taskId,
            String username,
            String message,
            WebSocketSession session) {

        socketUserManager.setSocket(session, taskId, username);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-in");
        generatedMessage.setUserLoggedIn(username);

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnClose
    public Publisher<GuiSocketMessage> onClose(
            UUID taskId,
            String username,
            WebSocketSession session) {

        socketUserManager.closeSocket(session, taskId, username);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-out");
        generatedMessage.setUserLoggedIn(username);

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }
}
