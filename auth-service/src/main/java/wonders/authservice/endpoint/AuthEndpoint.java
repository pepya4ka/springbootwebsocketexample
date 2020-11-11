package wonders.authservice.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wonders.authservice.model.Account;
import wonders.authservice.model.ApiErrorResponse;
import wonders.authservice.model.ApiResponse;
import wonders.authservice.payload.AuthRequest;
import wonders.authservice.payload.RegistrationRequest;
import wonders.authservice.service.AccountService;

import java.net.URI;
import java.util.HashMap;

@RestController
@Log4j2
public class AuthEndpoint {

    @Autowired
    private AccountService accountService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {
        log.info("logining user {}", loginRequest.getUsername());

        String token = accountService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        HashMap<String, Object> results = new HashMap<>();
        results.put("accessToken", token);

        return ResponseEntity.ok(new ApiResponse("SUCCESS", results, "Authorization", "auth"));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody RegistrationRequest payload) {
        log.info("creating user {}", payload.getUsername());

        Account account = Account
                .builder()
                .username(payload.getUsername())
                .password(payload.getPassword())
                .build();

        try {
            accountService.registerUser(account);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new ApiErrorResponse("INVALID_DATA", new String[]{}, "Authorization", "register", e.getMessage()));
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(account.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse("SUCCESS", new HashMap<>(), "Authorization", "register"));
    }
}