package com.jatinsinghroha.volleywithjson.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class State implements Serializable {

    @SerializedName("state_id")
    int stateID;

    @SerializedName("state_name")
    String stateName;

    boolean isExpanded = false;

    ListOfDistricts mDistrictList = null;

    public ListOfDistricts getDistrictList() {
        return mDistrictList;
    }

    public void setDistrictList(ListOfDistricts districtList) {
        mDistrictList = districtList;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
