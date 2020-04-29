package com.ida.smarttv.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Idea {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idea")
    @Expose
    private String idea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

}
