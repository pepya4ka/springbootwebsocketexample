package wonders.authservice.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wonders.authservice.model.Account;

import java.util.Optional;

//TODO
@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
