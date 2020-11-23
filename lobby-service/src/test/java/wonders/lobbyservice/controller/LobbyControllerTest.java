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

class LobbyControllerTest extends AbstractIntegrationTest {

    private String URL;

    private CompletableFuture<ApiResponse> completableFuture;

    private ApiRequest defaultCreateRequest;

    @BeforeEach
    public void fillDefaultApiRequest(){
        HashMap<String, String> results = new HashMap<>();
        results.put("playerName", "playerName");
        results.put("lobbyName", "lobbyName");
        results.put("maxPlayers", "6");
        results.put("moveTime", "00:00:12");
        defaultCreateRequest = new ApiRequest(new ApiRequest.Data("create",results));
    };

    @BeforeEach
    public void setup() {
        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/main";
    }

    @Test
    public void createLobby_LobbyCreated() throws InterruptedException, ExecutionException, TimeoutException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);

        stompSession.subscribe("/topic/lobby", new CreateLobbyStompFrameHandler());
        stompSession.send("/app/create", defaultCreateRequest);

        ApiResponse apiResponse = completableFuture.get(10, SECONDS);

        assertNotNull(apiResponse);
    }

    @Test
    public void deleteLobby_LobbyDeleted () throws InterruptedException, ExecutionException, TimeoutException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);

        stompSession.subscribe("/topic/lobby", new CreateLobbyStompFrameHandler());
        stompSession.send("/app/create", defaultCreateRequest);

        ApiResponse apiCreateResponse = completableFuture.get(10, SECONDS);
        assertNotNull(apiCreateResponse);
        assert (apiCreateResponse.getResults().containsKey("lobbyId"));

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("lobbyId", apiCreateResponse.getResults().get("lobbyId"));
        ApiRequest deleteRequest = new ApiRequest(new ApiRequest.Data("delete", attributes));

        stompSession.subscribe("/topic/lobby", new CreateLobbyStompFrameHandler());
        stompSession.send("/app/delete", deleteRequest);

        completableFuture.complete(apiCreateResponse);
        completableFuture = new CompletableFuture<>();
        ApiResponse apiDeleteResponse = completableFuture.get(10, SECONDS);

        assertNotNull(apiDeleteResponse);
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
