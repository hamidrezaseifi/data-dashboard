package de.mediqon.generic.data_dashboard.services.impl;

import de.mediqon.generic.data_dashboard.services.ISocketTaskUserMessageManager;
import de.mediqon.generic.data_dashboard.models.ui.WebSocketSessionUserModel;
import io.micronaut.scheduling.annotation.Scheduled;
import io.micronaut.websocket.WebSocketSession;


import javax.inject.Singleton;
import java.util.*;

@Singleton
public class SocketTaskUserMessageManager implements ISocketTaskUserMessageManager {


    private Map<Map<UUID, String>, WebSocketSessionUserModel> sessionUserMap = new HashMap<>();

    @Override
    public void openSocket(WebSocketSession session,
                           UUID taskId,
                           String username) {

        sessionUserMap.put(generateKey(taskId, username),
                           new WebSocketSessionUserModel(session, taskId, username));
    }

    @Override
    public void setSocket(WebSocketSession session,
                          UUID taskId,
                          String username) {

        sessionUserMap.put(generateKey(taskId, username),
                           new WebSocketSessionUserModel(session, taskId, username));
    }

    @Override
    public void closeSocket(WebSocketSession session,
                            UUID taskId,
                            String username) {
        sessionUserMap.remove(generateKey(taskId, username));
    }

    @Override
    public void sendMessage(UUID taskId,
                            String username,
                            Object message) {
        Map<UUID, String> key = generateKey(taskId, username);
        if(sessionUserMap.containsKey(key)){
            WebSocketSessionUserModel sessionUserModel = sessionUserMap.get(key);
            if(sessionUserModel.isClosed()){
                sessionUserMap.remove(key);
            }
            else{
                sessionUserModel.sendMessageAsync(message);
            }
        }
    }

    @Scheduled(fixedDelay = "45s", initialDelay = "5s")
    public void clearList(){

        List<Map<UUID, String>> closedSessions = new ArrayList<>();
        for(Map<UUID, String> key: sessionUserMap.keySet()){
            if(sessionUserMap.get(key).isClosed()){
                closedSessions.add(key);
                sessionUserMap.remove(key);
            }
        }

        for(Map<UUID, String> key: closedSessions){
            sessionUserMap.remove(key);
        }
    }

    private Map<UUID, String> generateKey(UUID taskId,
                               String username){
        Map<UUID, String> key = new HashMap<>();
        key.put(taskId, username);
        return  key;
    }
}
