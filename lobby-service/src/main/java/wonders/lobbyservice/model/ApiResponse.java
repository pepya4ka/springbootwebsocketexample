package wonders.lobbyservice.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiResponse {
    private String status;
    private HashMap<String, String> results;
    private String module;
    private String type;
}
