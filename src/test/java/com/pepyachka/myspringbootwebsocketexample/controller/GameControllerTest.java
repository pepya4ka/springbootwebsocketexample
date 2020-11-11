package com.pepyachka.myspringbootwebsocketexample.controller;

import com.pepyachka.myspringbootwebsocketexample.AbstractIntegrationTest;
import com.pepyachka.myspringbootwebsocketexample.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameControllerTest extends AbstractIntegrationTest {

    private String URL;

    private CompletableFuture<GameState> completableFuture;

    @BeforeEach
    public void setup() {
        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/main";
    }

    @Test
    public void testCreateGameEndpoint() throws InterruptedException, ExecutionException, TimeoutException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);

        stompSession.subscribe("/topic/game/", new CreateGameStompFrameHandler());
        stompSession.send("/app/create/", new GameState(1));
        stompSession.send("/app/create/", new GameState(1));
        stompSession.send("/app/create", new GameState(1));

        GameState gameState = completableFuture.get(10, SECONDS);

        assertNotNull(gameState);
    }

    private class CreateGameStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return GameState.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((GameState) o);
        }
    }
}