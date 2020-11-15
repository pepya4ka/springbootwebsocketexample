package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.LobbiesEntity;

@Repository
public interface LobbyRepository extends CrudRepository <LobbiesEntity, Integer> {
    @Override
    void deleteById(Integer integer);
}
