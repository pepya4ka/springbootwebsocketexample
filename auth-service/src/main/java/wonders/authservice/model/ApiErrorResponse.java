package wonders.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private String status;
    private String[] results;
    private String module;
    private String type;
    @JsonProperty("error_message")
    private String errorMessage;
}
