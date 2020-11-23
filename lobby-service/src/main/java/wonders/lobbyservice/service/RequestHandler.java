package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.LobbyEntity;
import wonders.lobbyservice.model.PlayerEntity;

import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Service
public class RequestHandler {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private LobbyService lobbyService;

    /*
     * @return attributes to response
     */
    public HashMap<String, String> createLobby(HashMap<String, String> attributes) throws IllegalArgumentException {
        PlayerEntity player = new PlayerEntity();

        //TODO добавить начальный статус игрока, мб в enum
        if (attributes.containsKey("playerName")) {
            player.setUsername(String.valueOf(attributes.get("playerName")));
        } else {
            throw new IllegalArgumentException("missing player name attribute");
        }

        LobbyEntity lobby = new LobbyEntity();
        lobby.setPlayers(Collections.singletonList(player));
        lobby.setOwner(player);

        //TODO add exceptions
        if (attributes.containsKey("lobbyName")) {
            lobby.setName(attributes.get("lobbyName"));
        } else {
            throw new IllegalArgumentException("missing lobby name attribute");
        }
        if (attributes.containsKey("maxPlayers")) {
            lobby.setMaxPlayers(Integer.valueOf(attributes.get("maxPlayers")));
        } else {
            throw new IllegalArgumentException("missing max player attribute");
        }
        if (attributes.containsKey("moveTime")) {
            lobby.setMoveTime(Time.valueOf(attributes.get("moveTime")));
        } else {
            throw new IllegalArgumentException("missing move time attribute");
        }

        lobby = lobbyService.save(lobby);

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
    public HashMap<String, String> deleteLobby (HashMap<String, String> attributes) throws IllegalArgumentException {
        if(attributes.containsKey("lobbyId")){
            Long lobbyId = (Long.valueOf(attributes.get("lobbyId")));
            Optional<LobbyEntity> lobby = lobbyService.findById(lobbyId);

            if(lobby.isEmpty()) {
                throw new IllegalArgumentException(String.format("lobby with '%d' id doesn't exist", lobbyId));
            }

            lobbyService.deleteById(Long.valueOf(attributes.get("lobbyId")));
        } else {
            throw new IllegalArgumentException("missing lobby id attribute");
        }

        HashMap<String, String> results = new HashMap<>();
        results.put("lobbyId", attributes.get("lobbyId"));

        return results;
    }

    /*
     * @return attributes to response
     */
    @Transactional
    public HashMap<String, String> connectPlayer(HashMap<String, String> attributes) throws IllegalArgumentException {
        PlayerEntity lobbyPlayer = new PlayerEntity();
        Optional<LobbyEntity> lobby;

        if(attributes.containsKey("lobbyId")) {
            Long lobbyId = (Long.valueOf(attributes.get("lobbyId")));

            lobby = lobbyService.findById(lobbyId);

            if(lobby.isEmpty()) {
                throw new IllegalArgumentException(String.format("lobby with '%d' id doesn't exist", lobbyId));
            }

        } else {
            throw new IllegalArgumentException("missing lobby id attribute");
        }

        if(attributes.containsKey("playerName")) {
            lobbyPlayer.setUsername(attributes.get("playerName"));
        } else {
            throw new IllegalArgumentException("missing player name attribute");
        }

        LobbyEntity lobbyToUpdate = lobby.get();
        lobbyToUpdate.addPlayer(lobbyPlayer);
        lobbyService.save(lobbyToUpdate);

        HashMap<String, String> result = new HashMap<>();
        result.put("lobbyId", lobbyToUpdate.getId().toString());
        result.put("playerId", String.valueOf(lobbyPlayer.getId()));
        result.put("playerName", lobbyPlayer.getUsername());

        return result;
    }
}
