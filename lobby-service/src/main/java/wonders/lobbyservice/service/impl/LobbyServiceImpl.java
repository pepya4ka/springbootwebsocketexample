package wonders.lobbyservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wonders.lobbyservice.model.LobbyEntity;
import wonders.lobbyservice.model.exception.NotFoundException;
import wonders.lobbyservice.repository.LobbyRepository;
import wonders.lobbyservice.service.LobbyService;

import java.util.Optional;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    LobbyRepository lobbyRepository;

    @Override
    @Transactional
    public LobbyEntity save(LobbyEntity entity) {
        return lobbyRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        lobbyRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return lobbyRepository.existsById(id);
    }

    @Override
    public Optional<LobbyEntity> findById(Long id) {
        return lobbyRepository.findById(id);
    }
}
