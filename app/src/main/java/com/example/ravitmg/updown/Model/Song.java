package com.example.ravitmg.updown.Model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ravitmg on 30/01/18.
 */

public class Song {
    private String sid;
    private String name;
    private int prediction;
    private Map<String,Integer> release;
    private int attempted;
    private int correctlyattempted;

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    public int getCorrectlyattempted() {
        return correctlyattempted;
    }

    public void setCorrectlyattempted(int correctlyattempted) {
        this.correctlyattempted = correctlyattempted;
    }

    public Map<String, Integer> getRelease() {
        return release;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setRelease(Map<String, Integer> release) {
        this.release = release;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }







    /*Song(JSONObject jsonObject){
        this.sid = jsonObject.getString("sid");
    }*/
}
