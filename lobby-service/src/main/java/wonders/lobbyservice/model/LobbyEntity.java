package wonders.lobbyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "lobby_id")
    private List<PlayerEntity> players;

    public void addPlayer(PlayerEntity player) {
        if(players == null) {
            players = new LinkedList<>();
        }

        players.add(player);
    }
}
