package wonders.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    public Account(Account account) {
        this.id = account.id;
        this.username = account.username;
        this.password = account.password;
        this.active = account.active;
        this.role = account.role;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    @Id
    @Column(name = "id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "role")
    String role;
}
