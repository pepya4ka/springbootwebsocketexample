package wonders.lobbyservice.model;

import lombok.*;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRequest {

    @Getter
    @Setter
    private Data data;

    @AllArgsConstructor
    public static class Data {

        @Getter
        @Setter
        private String type;

        @Getter
        @Setter
        private HashMap<String, String> attributes;

    }


}
