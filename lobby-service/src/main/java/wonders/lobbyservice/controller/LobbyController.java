package wonders.lobbyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wonders.lobbyservice.model.ApiRequest;
import wonders.lobbyservice.model.ApiResponse;
import wonders.lobbyservice.service.RequestHandler;

import java.util.HashMap;

@Controller
public class LobbyController {
    @Autowired
    RequestHandler requestHandler;

    @MessageMapping("/create")
    @SendTo("/topic/lobby")
    public ApiResponse create(ApiRequest apiRequest) {
        HashMap<String, String> results = new HashMap<>();
        try {
            results = requestHandler.createLobby(apiRequest.getData().getAttributes());
        } catch (IllegalArgumentException e) {

            return ApiResponse.builder()
                    .status("INVALID_DATA")
                    .results(results)
                    .module("Lobby")
                    .type("create")
                    .build();
        }

        return ApiResponse.builder()
                .status("SUCCESS")
                .results(results)
                .module("Lobby")
                .type("create")
                .build();
    }

    @MessageMapping("/delete")
    @SendTo("/topic/lobby")
    public ApiResponse delete(ApiRequest apiRequest) {
        HashMap<String, String> results = new HashMap<>();
        try {
            results = requestHandler.deleteLobby(apiRequest.getData().getAttributes());
        } catch (IllegalArgumentException e) {

            return ApiResponse.builder()
                    .status("INVALID_DATA")
                    .results(results)
                    .module("Lobby")
                    .type("delete")
                    .build();
        }

        return ApiResponse.builder()
                .status("SUCCESS")
                .results(results)
                .module("Lobby")
                .type("delete")
                .build();
    }

    @MessageMapping("/delete")
    @SendTo("/topic/lobby")
    public ApiResponse connectPlayer(ApiRequest apiRequest) {
        HashMap<String, String> results = new HashMap<>();
        try {
            results = requestHandler.connectPlayer(apiRequest.getData().getAttributes());
        } catch (IllegalArgumentException e) {

            return ApiResponse.builder()
                    .status("INVALID_DATA")
                    .results(results)
                    .module("Lobby")
                    .type("connect")
                    .build();
        }

        return ApiResponse.builder()
                .status("SUCCESS")
                .results(results)
                .module("Lobby")
                .type("connect")
                .build();
    }

    @MessageMapping("/start/")
    @SendTo("/topic/lobby")
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

}
