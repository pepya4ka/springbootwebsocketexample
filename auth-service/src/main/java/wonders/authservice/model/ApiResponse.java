package wonders.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String status;
    private HashMap<String, Object> results;
    private String module;
    private String type;
}