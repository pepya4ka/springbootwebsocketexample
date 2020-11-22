package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.LobbyOwnerEntity;
import wonders.lobbyservice.model.LobbyOwnerEntityPK;

@Repository
public interface LobbyOwnerRepository extends CrudRepository<LobbyOwnerEntity, LobbyOwnerEntityPK> {
}
