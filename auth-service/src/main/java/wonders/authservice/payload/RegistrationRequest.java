package wonders.authservice.payload;

import lombok.Data;

@Data
public class RegistrationRequest {
    private Data data;

    @lombok.Data
    private class Data {
        private String type;
        private Attributes attributes;

        @lombok.Data
        private class Attributes {
            String username;
            String password;
        }
    }

    public String getUsername() {
        return data.attributes.username;
    }
    public String getPassword() {
        return data.attributes.password;
    }
}
