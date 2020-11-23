package wonders.lobbyservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class RequestHandlerTests {

    @Autowired
    private RequestHandler requestHandler;

    private HashMap<String, String> defaultCreateLobbyAttributes = new HashMap<>();
    private HashMap<String, String> defaultDeleteLobbyAttributes = new HashMap<>();
    private HashMap<String, String> defaultUpdatePlayerStatusAttributes = new HashMap<>();

    @BeforeEach
    public void fillDefaultApiRequest() {
        defaultCreateLobbyAttributes.put("playerName", "playerName");
        defaultCreateLobbyAttributes.put("lobbyName", "lobbyName");
        defaultCreateLobbyAttributes.put("maxPlayers", "6");
        defaultCreateLobbyAttributes.put("moveTime", "00:00:12");
    }

   @Test
    public void createLobby_LobbyCreated() {
        //get
        HashMap<String, String> attributes = defaultCreateLobbyAttributes;

        //run
        HashMap<String, String> result = requestHandler.createLobby(defaultCreateLobbyAttributes);

        //assert
        assert (!result.isEmpty());
    }

    @Test
    public void deleteLobby_LobbyDeleted() {
        //get
        HashMap<String, String> attributes = defaultCreateLobbyAttributes;

        //run
        HashMap<String, String> resultCreate = requestHandler.createLobby(defaultCreateLobbyAttributes);
        defaultDeleteLobbyAttributes.put("lobbyId", resultCreate.get("lobbyId"));
        defaultUpdatePlayerStatusAttributes.put("playerId", resultCreate.get("ownerId"));
        defaultUpdatePlayerStatusAttributes.put("state", "1");
        HashMap<String, String> result = requestHandler.deleteLobby(defaultDeleteLobbyAttributes);


        //assert
        assert (!result.isEmpty());
    }

    @Test
    public void updatePlayerStatus() {
        //get
        HashMap<String, String> attributes = defaultCreateLobbyAttributes;

        //run
        HashMap<String, String> resultCreate = requestHandler.createLobby(defaultCreateLobbyAttributes);
        defaultUpdatePlayerStatusAttributes.put("lobbyId", resultCreate.get("lobbyId"));
        HashMap<String, String> result = requestHandler.updatePlayerStatus(defaultCreateLobbyAttributes);

        //assert
        assert (!result.isEmpty());
    }

    @Test
    public void connectPlayer_PLayerConnected() {
        //get
        HashMap<String, String> connectPlayerAttributes = new HashMap<>();
        connectPlayerAttributes.put("playerName", "Player Name");

        //run
        HashMap<String, String> createResult = requestHandler.createLobby(defaultCreateLobbyAttributes);
        connectPlayerAttributes.put("lobbyId", createResult.get("lobbyId"));

        HashMap<String, String> connectResult = requestHandler.connectPlayer(connectPlayerAttributes);

        //assert
        assert (!connectResult.isEmpty());
    }

    @Test
    public void disconnectPlayer_PLayerDisconnected() {
        //get
        HashMap<String, String> connectPlayerAttributes = new HashMap<>();
        connectPlayerAttributes.put("playerName", "Player Name");

        HashMap<String, String> disconnectPlayerAttributes = new HashMap<>();

        //run
        HashMap<String, String> createResult = requestHandler.createLobby(defaultCreateLobbyAttributes);
        connectPlayerAttributes.put("lobbyId", createResult.get("lobbyId"));

        HashMap<String, String> connectResult = requestHandler.connectPlayer(connectPlayerAttributes);

        disconnectPlayerAttributes.put("lobbyId", connectResult.get("lobbyId"));
        disconnectPlayerAttributes.put("playerId", connectResult.get("playerId"));
        HashMap<String, String> disconnectResult = requestHandler.disconnectPlayer(disconnectPlayerAttributes);

        //assert
        assert (!disconnectResult.isEmpty());
    }
}
