package wonders.lobbyservice.service;

import org.springframework.stereotype.Service;
import wonders.lobbyservice.model.LobbyEntity;

@Service
public interface LobbyService {
    LobbyEntity save(LobbyEntity entity);
    void deleteById(Long id);
    boolean existById(Long id);
    LobbyEntity findById(Long id) throws Exception;
}
