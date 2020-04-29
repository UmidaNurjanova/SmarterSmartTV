package com.ida.smarttv.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventIdeaResponse {

    @SerializedName("ideas")
    @Expose
    private List<Idea> ideas = null;

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }
}
