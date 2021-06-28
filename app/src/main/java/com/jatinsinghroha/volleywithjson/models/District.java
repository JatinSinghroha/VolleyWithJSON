package com.jatinsinghroha.volleywithjson.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class District implements Serializable
{

    @SerializedName("district_id")
    @Expose
    private Long districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    private final static long serialVersionUID = -3294552333634266236L;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
