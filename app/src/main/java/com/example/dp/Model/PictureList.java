package com.example.dp.Model;

import java.util.ArrayList;

public class PictureList {
    private ArrayList<Picture> pictures;

    public PictureList(ArrayList<Picture> results) {
        this.pictures = results;
    }

    public ArrayList<Picture> getResults() {
        return pictures;
    }

    public void setResults(ArrayList<Picture> results) {
        this.pictures = results;
    }
}
