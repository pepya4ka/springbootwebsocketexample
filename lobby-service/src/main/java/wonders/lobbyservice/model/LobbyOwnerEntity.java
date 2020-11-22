package wonders.lobbyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lobby_owner", schema = "heroku_7edbe89173452c0")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyOwnerEntity {
    @EmbeddedId
    private LobbyOwnerEntityPK pk;
}
