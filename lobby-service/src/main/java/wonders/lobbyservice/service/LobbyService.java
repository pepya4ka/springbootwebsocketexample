package wonders.lobbyservice.service;

import org.springframework.stereotype.Service;
import wonders.lobbyservice.model.LobbyEntity;

import java.util.Optional;

@Service
public interface LobbyService {
    LobbyEntity save(LobbyEntity entity);
    void deleteById(Long id);
    boolean existById(Long id);
    Optional<LobbyEntity> findById(Long id);
}
