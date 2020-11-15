package wonders.lobbyservice.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "lobbys")
@Data
public class LobbiesEntity {

    @GeneratedValue(strategy=GenerationType.AUTO)

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name ="max_players")
    private Integer maxPlayers;

    @Column(name = "move_time")
    private Time moveTime;

    public LobbiesEntity() {
    }
}
