package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.PlayerEntity;

@Repository
public interface LobbyPlayersRepository extends CrudRepository<PlayerEntity, String> {
    PlayerEntity findByUsername (String username);
}
