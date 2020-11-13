package wonders.lobbyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
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
