package wonders.lobbyservice.service;

import org.springframework.stereotype.Service;
import wonders.lobbyservice.model.LobbyEntity;
import wonders.lobbyservice.model.exception.NotFoundException;

import java.util.Optional;

@Service
public interface LobbyService {
    LobbyEntity save(LobbyEntity entity);
    void deleteById(Long id);
    boolean existById(Long id);
    Optional<LobbyEntity> findById(Long id);
}
