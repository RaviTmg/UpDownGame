package com.example.ravitmg.updown.Model;

/**
 * Created by ravitmg on 30/01/18.
 */

public class Song {
    private String sid;
    private String name;
    private int relyr, relmon, reldate;
    private int prediction;

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

    public String getSid() {
        return sid;
    }


    public int getRelyr() {
        return relyr;
    }


    public int getRelmon() {
        return relmon;
    }


    public int getReldate() {
        return reldate;
    }

    public Song(String sid, String name, int relyr, int relmon, int reldate, int prediction) {
        this.sid = sid;
        this.name = name;
        this.relyr = relyr;
        this.relmon = relmon;
        this.reldate = reldate;
        this.prediction = prediction;
    }

    /*Song(JSONObject jsonObject){
        this.sid = jsonObject.getString("sid");
    }*/
}
