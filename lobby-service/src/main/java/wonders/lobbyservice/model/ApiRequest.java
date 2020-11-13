package wonders.lobbyservice.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiRequest {
    private Data data;

    @lombok.Data
    private class Data {
        private String type;
        private HashMap<String, String> attributes;
    }
}
