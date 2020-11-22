package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.LobbyEntity;
import wonders.lobbyservice.model.LobbyOwnerEntity;
import wonders.lobbyservice.model.LobbyOwnerEntityPK;
import wonders.lobbyservice.model.PlayerEntity;

import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;

@Service
public class RequestHandler {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private LobbyOwnerService lobbyOwnerService;

    /*
     * @return attributes to response
     */
    public HashMap<String, String> createLobby(HashMap<String, String> attributes) throws Exception {
        PlayerEntity player = new PlayerEntity();

        //TODO добавить начальный статус игрока, мб в enum
        if (attributes.containsKey("playerName")) {
            player.setUsername(String.valueOf(attributes.get("playerName")));
        }

        LobbyEntity lobby = new LobbyEntity();
        lobby.setPlayers(Collections.singletonList(player));

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

        lobby = lobbyService.save(lobby);

        LobbyOwnerEntity owner = new LobbyOwnerEntity();
        owner.setPk(new LobbyOwnerEntityPK(lobby.getId(), player.getId()));
        lobbyOwnerService.save(owner);

        HashMap<String, String> results = new HashMap<>();
        results.put("ownerName", player.getUsername());
        results.put("ownerId", player.getId().toString());
        results.put("lobbyId", lobby.getId().toString());
        results.put("lobbyName", lobby.getName());
        results.put("maxPlayers", lobby.getMaxPlayers().toString());
        results.put("movieTime", lobby.getMoveTime().toString());

        return results;
    }

    /*
     * @return attributes to response
     */
    @Transactional
    public HashMap<String, String> deleteLobby (HashMap<String, String> attributes) throws Exception {
        int lobbyId;
        if(attributes.containsKey("lobbyId")){
            lobbyId = Integer.parseInt(attributes.get("lobbyId"));
            //lobbyRepository.deleteById(lobbyId);
        } else {
            throw new Exception();
        }

        HashMap<String, String> results = new HashMap<>();
        results.put("lobbyId", Integer.toString(lobbyId));

        return results;
    }

    /*
     * @return attributes to response
     */
    @Transactional
    public HashMap<String, String> connectPlayer(HashMap<String, String> attributes) throws Exception {
        PlayerEntity lobbyPlayer = new PlayerEntity();

        if(attributes.containsKey("lobbyId")) {
            //lobbyPlayer.setLobbyId(Integer.valueOf(attributes.get("lobbyId")));
        } else {
            throw new Exception();
        }

        if(attributes.containsKey("playerName")) {
            lobbyPlayer.setUsername(attributes.get("playerName"));
        } else {
            throw new Exception();
        }

        //lobbyPlayer = lobbyPlayersRepository.save(lobbyPlayer);

        HashMap<String, String> result = new HashMap<>();
        //result.put("lobbyId", lobbyPlayer.getLobbyId().toString());
        result.put("playerId", String.valueOf(lobbyPlayer.getId()));
        result.put("playerName", lobbyPlayer.getUsername());

        return result;
    }
}
