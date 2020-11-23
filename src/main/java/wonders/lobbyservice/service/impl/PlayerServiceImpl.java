package wonders.lobbyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.PlayerEntity;
import wonders.lobbyservice.repository.PlayerRepository;
import wonders.lobbyservice.service.PlayerService;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository repository;

    @Override
    @Transactional
    public PlayerEntity save(PlayerEntity entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public Optional<PlayerEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(PlayerEntity playerEntity) {
        repository.delete(playerEntity);
    }

    @Override
    @Transactional
    public Integer countAllPlayersInLobby(Long lobbyId) {
        return repository.countAllByLobbyId(lobbyId);
    }
}
