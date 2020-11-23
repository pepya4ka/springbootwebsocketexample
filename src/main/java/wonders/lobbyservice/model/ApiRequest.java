package wonders.lobbyservice.model;

import lombok.*;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRequest {

    private Data data;

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Data {

        private String type;
        private HashMap<String, String> attributes;

    }

}
