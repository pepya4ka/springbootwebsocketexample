package com.pepyachka.myspringbootwebsocketexample.controller;

import com.pepyachka.myspringbootwebsocketexample.model.GameState;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/game.table")
    public GameState gameStatus(GameState gameState) {
       return new GameState();
    }

    @MessageMapping("/game.start")
    public GameState gameStart(GameState gameState) {
       return new GameState();
    }

    @MessageMapping("/game.finish")
    public GameState gameFinish(GameState gameState) {
       return new GameState();
    }

}
