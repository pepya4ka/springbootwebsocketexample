package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wonders.lobbyservice.repository.LobbyPlayersRepository;

import java.util.HashMap;

@Service
public class LobbyPlayersService {
    @Autowired
    LobbyPlayersRepository lobbyPlayersRepository;

    public void createLobby(HashMap<String, Object> requestAttributes) {

    }
}
