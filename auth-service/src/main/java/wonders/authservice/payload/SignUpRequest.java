package wonders.authservice.payload;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
