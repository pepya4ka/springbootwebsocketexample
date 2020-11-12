package com.pepyachka.myspringbootwebsocketexample.controller;

import com.pepyachka.myspringbootwebsocketexample.model.GameState;
import com.pepyachka.myspringbootwebsocketexample.model.PlayerStat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @MessageMapping("/start/")
    @SendTo("/topic/lobby/")
    public GameState start() {
        return new GameState(1);
    }

    @MessageMapping("/create/")
    @SendTo("/topic/lobby/")
    public GameState create() {
        return new GameState(1);
    }

    @MessageMapping("/connect/")
    @SendTo("/topic/lobby/")
    public GameState connect() {
        return new GameState(1);
    }

    @MessageMapping("/disconnect/")
    @SendTo("/topic/lobby/")
    public GameState disconnect() {
        return new GameState(1);
    }

    @MessageMapping("/update/")
    @SendTo("/topic/lobby/")
    public GameState update() {
        return new GameState(1);
    }

    @MessageMapping("/delete/")
    @SendTo("/topic/lobby/")
    public GameState delete() {
        return new GameState(1);
    }


}
