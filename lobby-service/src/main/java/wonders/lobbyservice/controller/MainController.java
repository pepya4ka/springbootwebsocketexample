package wonders.lobbyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wonders.lobbyservice.model.ApiRequest;
import wonders.lobbyservice.model.ApiResponse;
import wonders.lobbyservice.model.GameState;
import wonders.lobbyservice.service.RequestHandler;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    RequestHandler requestHandler;

    @MessageMapping("/create/")
    @SendTo("/topic/lobby/")
    public ApiResponse create(ApiRequest apiRequest) {
        ApiResponse response = new ApiResponse();
        response.setStatus("SUCCESS");
        response.setResults(requestHandler.createLobby(apiRequest.getData().getAttributes()));
        response.setModule("Lobby");
        response.setType("Create");

        return response;
    }

    @MessageMapping("/start/")
    @SendTo("/topic/lobby/")
    public ApiResponse start(ApiRequest apiRequest) {
        return null;
    }

    @MessageMapping("/connect/")
    @SendTo("/topic/lobby/")
    public ApiResponse connect(ApiRequest apiRequest) {
        return null;
    }

    @MessageMapping("/disconnect/")
    @SendTo("/topic/lobby/")
    public ApiResponse disconnect(ApiRequest apiRequest) {
        return null;
    }

    @MessageMapping("/update/")
    @SendTo("/topic/lobby/")
    public ApiResponse update(ApiRequest apiRequest) {
        return null;
    }

    @MessageMapping("/delete/")
    @SendTo("/topic/lobby/")
    public ApiResponse delete(ApiRequest apiRequest) {
        return null;
    }

}
