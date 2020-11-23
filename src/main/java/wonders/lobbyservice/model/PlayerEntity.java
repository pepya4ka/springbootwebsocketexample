package wonders.lobbyservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Data
public class PlayerEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name="ready")
    private int ready;

    @Basic
    @Column(name = "lobby_id")
    private Long lobbyId;
}
