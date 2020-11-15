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
     * @return attributes to response
     */
    public HashMap<String, String> createLobby(HashMap<String, String> attributes) throws Exception {
        LobbiesEntity lobby = new LobbiesEntity();

        //TODO add exceptions
        if (attributes.containsKey("lobbyName")) {
            lobby.setName(attributes.get("lobbyName"));
        } else {
            throw new Exception();
        }
        if (attributes.containsKey("maxPlayers")) {
            lobby.setMaxPlayers(Integer.valueOf(attributes.get("maxPlayers")));
        } else {
            throw new Exception();
        }
        if (attributes.containsKey("moveTime")) {
            lobby.setMoveTime(Time.valueOf(attributes.get("moveTime")));
        } else {
            throw new Exception();
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
//        results.put("movieTime", lobby.getMoveTime().toString());

        return results;
    }

    /*
     * @return attributes to response
     */
    public HashMap<String, String> deleteLobby (HashMap<String, String> attributes) throws Exception {
        Integer lobbyId;
        if(attributes.containsKey("lobbyId")){
            lobbyId = Integer.parseInt(attributes.get("lobbyId"));
            lobbyRepository.deleteById(lobbyId);
        } else {
            throw new Exception();
        }

        HashMap<String, String> results = new HashMap<>();
        results.put("lobbyId", lobbyId.toString());

        return results;
    }

    /*
     * @return attributes to response
     */
    public HashMap<String, String> connectPlayer(HashMap<String, String> attributes) throws Exception {
        LobbyPlayersEntity lobbyPlayer = new LobbyPlayersEntity();

        if(attributes.containsKey("lobbyId")) {
            lobbyPlayer.setLobbyId(Integer.valueOf(attributes.get("lobbyId")));
        } else {
            throw new Exception();
        }

        if(attributes.containsKey("playerName")) {
            lobbyPlayer.setUsername(attributes.get("playerName"));
        } else {
            throw new Exception();
        }

        lobbyPlayer = lobbyPlayersRepository.save(lobbyPlayer);

        HashMap<String, String> result = new HashMap<>();
        result.put("lobbyId", lobbyPlayer.getLobbyId().toString());
        result.put("playerId", lobbyPlayer.getId().toString());
        result.put("playerName", lobbyPlayer.getUsername());

        return result;
    }
}
