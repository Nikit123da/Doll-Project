package com.example.doll_project;

import java.io.Serializable;

public class kidsData implements Serializable{
    private final String name;
    private final String emotion;
    private final int[] kidsPoints;

    public kidsData(String name, String emotion, int[] kidsPoints) {
        this.name = name;
        this.emotion = emotion;
        this.kidsPoints = kidsPoints;

    }

    public String getEmotion() {
        return emotion;
    }

    public String getName() {
        return name;
    }
    public int[] getKidsPoints() {
        return kidsPoints;
    }

}
