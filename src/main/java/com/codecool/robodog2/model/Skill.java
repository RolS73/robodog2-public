package com.codecool.robodog2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Skill {

    @JsonIgnore
    private long id;
    private long dog_Id;
    private long trick_Id;
    private long level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDog_Id() {
        return dog_Id;
    }

    public void setDog_Id(long dog_Id) {
        this.dog_Id = dog_Id;
    }

    public long getTrick_Id() {
        return trick_Id;
    }

    public void setTrick_Id(long trick_Id) {
        this.trick_Id = trick_Id;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
