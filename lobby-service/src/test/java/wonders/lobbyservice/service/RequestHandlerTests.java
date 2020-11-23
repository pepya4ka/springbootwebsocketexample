package wonders.lobbyservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.ApiRequest;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class RequestHandlerTests {

    @Autowired
    private RequestHandler requestHandler;

    private HashMap<String, String> defaultAttributes = new HashMap<>();

    @BeforeEach
    public void fillDefaultApiRequest() {
        defaultAttributes.put("playerName", "playerName");
        defaultAttributes.put("lobbyName", "lobbyName");
        defaultAttributes.put("maxPlayers", "6");
        defaultAttributes.put("moveTime", "00:00:12");
    }

    @Test
    public void createLobby_LobbyCreated() {
        //get
        HashMap<String, String> attributes = defaultAttributes;

        //run
        HashMap<String, String> result = requestHandler.createLobby(defaultAttributes);

        //assert
        assert (!result.isEmpty());
    }
}
