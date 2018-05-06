package com.vibhor.moviereviewer.model;

import com.google.gson.annotations.*;

public class Multimedia {

    @SerializedName("type")
    private String type;

    @SerializedName("src")
    private String src;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public String getSrc() {
        return src;
    }
}
