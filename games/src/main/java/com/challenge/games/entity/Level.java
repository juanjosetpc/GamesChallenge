package com.challenge.games.entity;

import com.challenge.games.dto.DTO;
import com.challenge.games.dto.LevelDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "levels")
public class Level implements DTO<LevelDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private LevelType name;

    public enum LevelType {
        INVENCIBLE, PRO, NOOB
    }

    public Level() {}

    public Level(LevelType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public LevelType getName() {
        return name;
    }

    public void setName(LevelType name) {
        this.name = name;
    }
    public LevelDTO toDTO() {
        return new LevelDTO(this.id, this.name.name());
    }
}
