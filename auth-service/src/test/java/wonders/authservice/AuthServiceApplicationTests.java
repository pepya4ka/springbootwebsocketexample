package wonders.authservice;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import wonders.authservice.model.Account;
import wonders.authservice.repositories.AccountRepository;

@SpringBootTest
@Log4j2
class AuthServiceApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void contextLoads() {
        accountRepository.findAll().forEach(log :: info);
    }

}
