package com.challenge.games.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
public class Level {

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
}
