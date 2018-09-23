package com.example.samsung.loginactivity;

/**
 * Created by samsung on 2018-09-10.
 */

public class User {
    private String rank;
    private String pts;
    private String id;
    public String getID(){
        return id;
    }
    public void setID(String id){
        this.id= id;
    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }



    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }
    public User() {
    }
    public User(String rank, String id, String pts) {
        this.rank = rank;
        this.id=id;
        this.pts = pts;
    }
}
