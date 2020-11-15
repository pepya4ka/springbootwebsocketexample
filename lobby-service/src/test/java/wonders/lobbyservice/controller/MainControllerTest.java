package wonders.lobbyservice.controller;

import wonders.lobbyservice.AbstractIntegrationTest;
import wonders.lobbyservice.model.ApiRequest;
import wonders.lobbyservice.model.ApiResponse;
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
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainControllerTest extends AbstractIntegrationTest {

    private String URL;

    private CompletableFuture<ApiResponse> completableFuture;

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


        HashMap<String, String> results = new HashMap<>();
        results.put("ownerName", "ownerName");
        results.put("ownerId", "ownerId");
        results.put("lobbyId", "lobbyId");
        results.put("lobbyName", "lobbyName");
        results.put("maxPlayers", "6");
//        results.put("movieTime", "movieTime");
        ApiRequest apiRequest = new ApiRequest(new ApiRequest.Data("create",results));

        stompSession.subscribe("/topic/lobby/", new CreateLobbyStompFrameHandler());
        stompSession.send("/app/create/", apiRequest);
        stompSession.send("/app/create/", apiRequest);
        stompSession.send("/app/create/", apiRequest);

        ApiResponse apiResponse = completableFuture.get(10, SECONDS);

        assertNotNull(apiResponse);
    }

    private class CreateLobbyStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return ApiResponse.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((ApiResponse) o);
        }
    }
}
