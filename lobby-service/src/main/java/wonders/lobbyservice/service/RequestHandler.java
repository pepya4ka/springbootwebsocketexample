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
    @Transactional
    public HashMap<String, String> createLobby(HashMap<String, String> attributes) throws IllegalArgumentException {

        LobbyEntity lobby = new LobbyEntity();

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

        PlayerEntity player = new PlayerEntity();

        //TODO добавить начальный статус игрока, мб в enum
        if (attributes.containsKey("playerName")) {
            player.setUsername(String.valueOf(attributes.get("playerName")));
        } else {
            throw new IllegalArgumentException("missing player name attribute");
        }

        player.setLobbyId(lobby.getId());
        player = playerService.save(player);

        lobby.setOwnerId(player.getId());

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
    public HashMap<String, String> connectPlayer(HashMap<String, String> attributes) throws IllegalArgumentException {
        PlayerEntity player = new PlayerEntity();
        Optional<LobbyEntity> lobby;

        if(attributes.containsKey("lobbyId")) {
            Long lobbyId = (Long.valueOf(attributes.get("lobbyId")));

            lobby = lobbyService.findById(lobbyId);

            if(lobby.isEmpty()) {
                throw new IllegalArgumentException(String.format("lobby with '%d' id doesn't exist", lobbyId));
            }
            player.setLobbyId(lobbyId);

        } else {
            throw new IllegalArgumentException("missing lobby id attribute");
        }

        if(attributes.containsKey("playerName")) {
            player.setUsername(attributes.get("playerName"));
        } else {
            throw new IllegalArgumentException("missing player name attribute");
        }

        playerService.save(player);

        HashMap<String, String> result = new HashMap<>();
        result.put("lobbyId", lobby.get().getId().toString());
        result.put("playerId", String.valueOf(player.getId()));
        result.put("playerName", player.getUsername());

        return result;
    }

    public HashMap<String, String> disconnectPlayer(HashMap<String, String> attributes) throws IllegalArgumentException {
        Optional<PlayerEntity> player;

        Long playerId;
        if(attributes.containsKey("playerId")) {
            playerId = Long.valueOf(attributes.get("playerId"));

            player = playerService.findById(playerId);
            if (player.isEmpty()) {
                throw new IllegalArgumentException(String.format("player with '%d' id doesn't exist", playerId));
            }

        } else {
            throw new IllegalArgumentException("missing player id attribute");
        }

        Long lobbyId;
        if(attributes.containsKey("lobbyId")) {
            lobbyId = Long.valueOf(attributes.get("lobbyId"));

            if (!player.get().getLobbyId().equals(lobbyId)) {
                throw new IllegalArgumentException(String.format("lobby with '%d' id doesn't contain player", lobbyId));
            }

        } else {
            throw new IllegalArgumentException("missing player id attribute");
        }

        playerService.delete(player.get());

        HashMap<String, String> result = new HashMap<>();
        result.put("lobbyId", lobbyId.toString());
        result.put("playerId", playerId.toString());

        return result;
    }

    public HashMap<String, String> updatePlayerStatus(HashMap<String, String> attributes) throws IllegalArgumentException {
        Optional<PlayerEntity> player;

        Long playerId;
        if(attributes.containsKey("playerId")) {
            playerId = Long.valueOf(attributes.get("playerId"));

            player = playerService.findById(playerId);
            if (player.isEmpty()) {
                throw new IllegalArgumentException(String.format("player with '%d' id doesn't exist", playerId));
            }

        } else {
            throw new IllegalArgumentException("missing player id attribute");
        }

        Long lobbyId;
        if(attributes.containsKey("lobbyId")) {
            lobbyId = Long.valueOf(attributes.get("lobbyId"));

            /*if (!player.get().getLobby().getId().equals(lobbyId)) {
                throw new IllegalArgumentException(String.format("lobby with '%d' id doesn't contain player", lobbyId));
            }

             */

        } else {
            throw new IllegalArgumentException("missing player id attribute");
        }

        int status;
        if(attributes.containsKey("state")) {
            status = Integer.parseInt(attributes.get("state"));
            player.get().setReady(status);
        } else {
            throw new IllegalArgumentException("missing player name attribute");
        }

        playerService.save(player.get());

        HashMap<String, String> result = new HashMap<>();
        result.put("lobbyId", lobbyId.toString());
        result.put("playerId", playerId.toString());
        result.put("state", Integer.toString(status));

        return result;
    }

    public HashMap<String, String> startGame(HashMap<String, String> attributes) throws IllegalArgumentException {
        return null;
    }
}
