package wonders.lobbyservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wonders.lobbyservice.model.ApiRequest;
import wonders.lobbyservice.model.ApiResponse;
import wonders.lobbyservice.model.GameState;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @MessageMapping("/create/")
    @SendTo("/topic/lobby/")
    public ApiResponse create(ApiRequest apiRequest) {

        HashMap<String, String> hashMapResponse = new HashMap<>();
        for (Map.Entry<String, String> entry : apiRequest.getData().getAttributes().entrySet()) {

            if (entry.getKey().equals("playerName"))
                hashMapResponse.put("ownerName", "ownerName");

            if (entry.getKey().equals("lobbyName"))
                hashMapResponse.put("lobbyName", "lobbyName");

            if (entry.getKey().equals("maxPlayers"))
                hashMapResponse.put("maxPlayers", "maxPlayers");

            if (entry.getKey().equals("moveTime"))
                hashMapResponse.put("moveTime", "moveTime");

        }
        hashMapResponse.put("ownerId", "ownerId");
        hashMapResponse.put("lobbyId", "lobbyId");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("SUCCESS");
        apiResponse.setModule("lobby");
        apiResponse.setType("create");
        return apiResponse;
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
