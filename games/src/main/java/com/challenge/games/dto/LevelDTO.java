package com.challenge.games.dto;

public class LevelDTO {
    private Integer id;
    private String levelType;

    public LevelDTO() {}

    public LevelDTO(Integer id, String levelType) {
        this.id = id;
        this.levelType = levelType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }
}
