package wonders.lobbyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.LobbyOwnerEntity;
import wonders.lobbyservice.repository.LobbyOwnerRepository;
import wonders.lobbyservice.service.LobbyOwnerService;

@Service
public class LobbyOwnerServiceImpl implements LobbyOwnerService {
    @Autowired
    private LobbyOwnerRepository repository;

    @Override
    @Transactional
    public LobbyOwnerEntity save(LobbyOwnerEntity entity) {
        return repository.save(entity);
    }
}
