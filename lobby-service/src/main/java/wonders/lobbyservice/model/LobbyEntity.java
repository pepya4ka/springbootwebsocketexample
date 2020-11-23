package wonders.lobbyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "lobbies", schema = "heroku_7edbe89173452c0")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LobbyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "max_players")
    private Integer maxPlayers;
    @Basic
    @Column(name = "move_time")
    private Time moveTime;
    @Basic
    @Column(name = "owner_id")
    private Long ownerId;
}
