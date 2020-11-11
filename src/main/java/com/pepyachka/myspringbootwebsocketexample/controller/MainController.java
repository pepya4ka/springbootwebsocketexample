package com.pepyachka.myspringbootwebsocketexample.controller;

import com.pepyachka.myspringbootwebsocketexample.model.GameState;
import com.pepyachka.myspringbootwebsocketexample.model.PlayerStat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @MessageMapping("/main.start")
    @SendTo("/game")
    public GameState start() {
        return new GameState(1);
    }

    @MessageMapping("/main.stat")
    public PlayerStat stat() {
        return new PlayerStat();
    }

    @MessageMapping("/main.search")
    public void search() {
        return;
    }
}
