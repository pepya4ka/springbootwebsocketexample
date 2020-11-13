package wonders.lobbyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wonders.lobbyservice.model.LobbysEntity;
import wonders.lobbyservice.repository.LobbyRepository;

import java.util.HashMap;

@Service
public class LobbysService {

    @Autowired
    LobbyRepository repository;

}
