package wonders.lobbyservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.lobbyservice.model.PlayerEntity;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
    Optional<PlayerEntity> findById (Long id);
}
