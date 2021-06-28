package com.jatinsinghroha.volleywithjson.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListOfDistricts implements Serializable
{

    @SerializedName("districts")
    @Expose
    private List<District> districts = null;

    @SerializedName("ttl")
    @Expose
    private Long ttl;

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

}
