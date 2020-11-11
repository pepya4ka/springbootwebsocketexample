package wonders.authservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wonders.authservice.exception.AccountAlreadyExistException;
import wonders.authservice.exception.BadLoginPasswordException;
import wonders.authservice.model.Account;
import wonders.authservice.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
public class AccountService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public String loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            return tokenProvider.generateToken(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public Account registerUser(Account account) throws AccountAlreadyExistException, BadLoginPasswordException {
        log.info("registering user {}", account.getUsername());

        if(account.getUsername() == null || account.getPassword() == null
                || account.getUsername().isEmpty() || account.getPassword().isEmpty()) {
            log.warn("login/password null");

            throw new BadLoginPasswordException("Логин/пароль не могут быть пустыми");
        }

        if(accountRepository.existsByUsername(account.getUsername())) {
            log.warn("username {} already exists.", account.getUsername());

            throw new AccountAlreadyExistException(String.format("Логин %s занят", account.getUsername()));
        }

        account.setActive(true);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole("User");

        return accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String login) {
        log.info("retrieving user {}", login);
        return accountRepository.findByUsername(login);
    }
}
