package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.LobbysEntity;

@Repository
public interface LobbyRepository extends CrudRepository <LobbysEntity, Integer> {
}
