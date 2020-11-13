package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wonders.lobbyservice.model.LobbyPlayersEntity;
import wonders.lobbyservice.model.LobbiesEntity;
import wonders.lobbyservice.repository.LobbyPlayersRepository;
import wonders.lobbyservice.repository.LobbyRepository;

import java.sql.Time;
import java.util.HashMap;

@Service
public class RequestHandler {

    @Autowired
    private LobbyPlayersRepository lobbyPlayersRepository;
    @Autowired
    private LobbyRepository lobbyRepository;

    /*
     * @returns attributes
     */
    public HashMap<String, String> createLobby(HashMap<String, String> attributes) {
        LobbiesEntity lobby = new LobbiesEntity();

        if (attributes.containsKey("lobbyName")) {
            lobby.setName(attributes.get("lobbyName"));
        }
        if (attributes.containsKey("maxPlayers")) {
            lobby.setMaxPlayers(Integer.valueOf(attributes.get("maxPlayers")));
        }
        if (attributes.containsKey("moveTime")) {
            lobby.setMoveTime(Time.valueOf(attributes.get("moveTime")));
        }

        lobby = lobbyRepository.save(lobby);

        LobbyPlayersEntity lobbyPlayer = new LobbyPlayersEntity();

        lobbyPlayer.setLobbyId(lobby.getId());
        //TODO добавить начальный статус игрока, мб в enum
        if (attributes.containsKey("playerName")) {
            lobbyPlayer.setUsername(String.valueOf(attributes.get("playerName")));
        }

        lobbyPlayersRepository.save(lobbyPlayer);

        HashMap<String, String> results = new HashMap<>();
        results.put("ownerName", lobbyPlayer.getUsername());
        results.put("ownerId", lobbyPlayer.getId().toString());
        results.put("lobbyId", lobby.getId().toString());
        results.put("lobbyName", lobby.getName());
        results.put("maxPlayers", lobby.getMaxPlayers().toString());
        results.put("movieTime", lobby.getMoveTime().toString());

        return results;
    }
}
