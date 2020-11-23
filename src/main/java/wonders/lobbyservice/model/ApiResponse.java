package wonders.lobbyservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String status;
    private HashMap<String, String> results;
    private String module;
    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error_message")
    private String error;
}
