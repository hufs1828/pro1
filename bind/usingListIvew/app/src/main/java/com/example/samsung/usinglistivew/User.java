package com.example.samsung.usinglistivew;

/**
 * Created by samsung on 2018-09-10.
 */

public class User {
    private String rank;
    private String name;
    private String pts;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public User(String rank, String name, String pts) {
        this.rank = rank;
        this.name = name;
        this.pts = pts;
    }
}
