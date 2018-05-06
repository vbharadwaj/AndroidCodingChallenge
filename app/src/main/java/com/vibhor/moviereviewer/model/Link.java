package com.vibhor.moviereviewer.model;

import com.google.gson.annotations.*;

public class Link {

    private String type;

    @SerializedName("suggested_link_text")
    private String suggestedLinkText;
    private String url;
}
