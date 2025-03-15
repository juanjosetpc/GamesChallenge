package com.challenge.games.dto;

import com.challenge.games.entity.Location;

import java.io.Serializable;

public class PlayerDTO implements Serializable {
    private Integer id;
    private String nickName;
    private String location;

    public PlayerDTO() {}

    public PlayerDTO(Integer id, String nickName, String location) {
        this.id = id;
        this.nickName = nickName;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}