package wonders.lobbyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.PlayerEntity;
import wonders.lobbyservice.repository.LobbyPlayersRepository;
import wonders.lobbyservice.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    LobbyPlayersRepository repository;

    @Override
    @Transactional
    public PlayerEntity save(PlayerEntity entity) {
        return repository.save(entity);
    }
}
