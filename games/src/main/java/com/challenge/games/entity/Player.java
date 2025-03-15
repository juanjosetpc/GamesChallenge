package com.challenge.games.entity;

import com.challenge.games.dto.DTO;
import com.challenge.games.dto.PlayerDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player implements DTO<PlayerDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public Player() {}

    public Player(String nickName, Location location) {
        this.nickName = nickName;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(Integer id) {
    }

    public PlayerDTO toDTO() {
        return new PlayerDTO(
                this.id,
                this.nickName,
                this.location != null ? this.location.getName() : null
        );
    }
}
