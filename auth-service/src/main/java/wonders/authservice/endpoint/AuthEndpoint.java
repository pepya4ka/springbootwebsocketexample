package wonders.authservice.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wonders.authservice.exception.AccountAlreadyExistException;
import wonders.authservice.exception.BadRequestException;
import wonders.authservice.model.Account;
import wonders.authservice.model.ApiResponse;
import wonders.authservice.payload.JwtAuthenticationResponse;
import wonders.authservice.payload.LoginRequest;
import wonders.authservice.payload.SignUpRequest;
import wonders.authservice.service.AccountService;

import java.net.URI;

@RestController
@Log4j2
public class AuthEndpoint {

    @Autowired
    private AccountService accountService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String token = accountService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/role")
    public String test(){
        return "ok";
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        Account account = Account
                .builder()
                .username(payload.getUsername())
                .password(payload.getPassword())
                .build();

        try {
            accountService.registerUser(account);
        } catch (AccountAlreadyExistException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(account.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true,"User registered successfully"));
    }
}