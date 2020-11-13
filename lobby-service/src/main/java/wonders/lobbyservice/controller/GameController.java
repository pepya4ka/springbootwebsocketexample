package wonders.lobbyservice.controller;

import wonders.lobbyservice.model.GameState;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {


    @MessageMapping("/game/create/")
    @SendTo("/topic/game/")
    public GameState create(GameState gameState) {
        return new GameState(1);
    }

}
