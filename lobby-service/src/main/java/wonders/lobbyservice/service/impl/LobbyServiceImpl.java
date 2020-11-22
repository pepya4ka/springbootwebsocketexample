package wonders.lobbyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.LobbyEntity;
import wonders.lobbyservice.repository.LobbyRepository;
import wonders.lobbyservice.service.LobbyService;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    LobbyRepository lobbyRepository;

    @Override
    @Transactional
    public LobbyEntity save(LobbyEntity entity) {
        return lobbyRepository.save(entity);
    }
}
