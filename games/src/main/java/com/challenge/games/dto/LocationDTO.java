package com.challenge.games.dto;

import java.io.Serializable;

public class LocationDTO implements Serializable {
    private Integer id;
    private String name;

    public LocationDTO() {}

    public LocationDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
