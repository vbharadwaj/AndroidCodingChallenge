package com.vibhor.moviereviewer.model;

import com.google.gson.annotations.*;

public class Movie {

    @SerializedName("display_title")
    private String title;

    @SerializedName("mpaa_rating")
    private String ratings;

    @SerializedName("critics_pick")
    private int criticsPick;

    @SerializedName("byline")
    private String byline;

    @SerializedName("headline")
    private String headline;

    @SerializedName("summary_short")
    private String summary;

    @SerializedName("publication_date")
    private String pubDate;

    @SerializedName("opening_date")
    private String openingDate;

    @SerializedName("date_updated")
    private String dateUpdated;

    @SerializedName("link")
    private Link link;

    @SerializedName("multimedia")
    private Multimedia multimedia;

    public String getTitle() {
        return title;
    }

    public String getRatings() {
        return ratings;
    }

    public String getByline() {
        return byline;
    }

    public String getHeadline() {
        return headline;
    }

    public String getSummary() {
        return summary;
    }

    public String getPubDate() {
        return pubDate;
    }

    public Link getLink() {
        return link;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }
}
