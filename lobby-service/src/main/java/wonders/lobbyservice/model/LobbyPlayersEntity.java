package wonders.lobbyservice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lobby_players")
@Data
public class LobbyPlayersEntity {
    @Id
    Integer id;

    @Column(name = "username")
    String username;

    @Column(name = "lobby_id")
    Integer lobbyId;

    @Column(name="ready")
    Integer ready;
}
