package com.jatinsinghroha.volleywithjson.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListOfStates implements Serializable
{

    private List<State> states = null;

    @SerializedName("ttl")
    private Long ttl;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

}
