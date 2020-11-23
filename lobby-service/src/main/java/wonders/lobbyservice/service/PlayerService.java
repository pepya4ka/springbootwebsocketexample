package wonders.lobbyservice.service;

import wonders.lobbyservice.model.PlayerEntity;

import java.util.Optional;

public interface PlayerService {
    PlayerEntity save(PlayerEntity entity);
    Optional<PlayerEntity> findById(Long id);
    void delete(PlayerEntity entity);
}
