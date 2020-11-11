package com.pepyachka.myspringbootwebsocketexample.controller;

import com.pepyachka.myspringbootwebsocketexample.model.GameState;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/create/")
    @SendTo("/topic/game/")
    public GameState gameStatus(GameState gameState) {
       return new GameState(1);
    }

    @MessageMapping("/game.start")
    public GameState gameStart(GameState gameState) {
       return new GameState(1);
    }

    @MessageMapping("/game.finish")
    public GameState gameFinish(GameState gameState) {
       return new GameState(1);
    }

}
