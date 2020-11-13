package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import wonders.lobbyservice.model.LobbyPlayersEntity;

public interface LobbyPlayersRepository extends CrudRepository<LobbyPlayersEntity, String> {
}
