package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wonders.lobbyservice.repository.LobbyRepository;

@Service
public class LobbiesService {

    @Autowired
    LobbyRepository repository;

}
