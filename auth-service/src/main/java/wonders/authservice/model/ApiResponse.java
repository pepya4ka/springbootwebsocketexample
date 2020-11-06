package wonders.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String status;
    private String[] results;
    private String module;
    private String type;
}