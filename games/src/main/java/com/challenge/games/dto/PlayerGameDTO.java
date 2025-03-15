package com.challenge.games.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class PlayerGameDTO implements Serializable {
    private Integer id;

    @NotNull(message = "Player ID cannot be null")
    private Integer playerId;

    private String playerNickName;
    private String location;
    private Integer gameId;

    @NotNull(message = "Game ID cannot be null")
    private String gameName;

    @NotEmpty(message = "Level type cannot be empty")
    @Pattern(regexp = "INVENCIBLE|PRO|NOOB", message = "Level type must be one of [INVENCIBLE, PRO, NOOB]")
    private String levelType;

    public PlayerGameDTO() {}

    public PlayerGameDTO(Integer id, Integer playerId, String playerNickName, String location, Integer gameId, String gameName, String levelType) {
        this.id = id;
        this.playerId = playerId;
        this.playerNickName = playerNickName;
        this.location = location;
        this.gameId = gameId;
        this.gameName = gameName;
        this.levelType = levelType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerNickName() {
        return playerNickName;
    }

    public void setPlayerNickName(String playerNickName) {
        this.playerNickName = playerNickName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }
}
