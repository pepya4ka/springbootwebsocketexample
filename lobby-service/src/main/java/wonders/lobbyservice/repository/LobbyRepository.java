package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.LobbyEntity;

@Repository
public interface LobbyRepository extends CrudRepository <LobbyEntity, Long> {
    LobbyEntity findByName(String name);
}
