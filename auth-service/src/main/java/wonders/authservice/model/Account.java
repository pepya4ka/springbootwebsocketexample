package wonders.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

//TODO
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class Account {

    public Account(Account account) {
        this.id = account.id;
        this.username = account.username;
        this.password = account.password;
        this.createdAt = account.createdAt;
        this.updatedAt = account.updatedAt;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    private ObjectId id;

    private String username;

    @JsonIgnore
    private String password;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private boolean active;
    String role;
}
