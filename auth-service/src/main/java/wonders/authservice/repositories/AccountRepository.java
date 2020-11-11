package wonders.authservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wonders.authservice.model.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
    Account save(Account account);
}
