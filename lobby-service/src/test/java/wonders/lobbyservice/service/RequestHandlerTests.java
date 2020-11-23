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
    public void connectPlayer_PLayerConnected() {
        //get
        HashMap<String, String> attributes = defaultCreateLobbyAttributes;

        //run
        HashMap<String, String> result = requestHandler.createLobby(defaultCreateLobbyAttributes);

        //assert
        assert (!result.isEmpty());
    }
}
