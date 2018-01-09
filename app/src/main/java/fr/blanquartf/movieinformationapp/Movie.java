package fr.blanquartf.movieinformationapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by blanquartf on 14/12/2017.
 */

public class Movie {
    @SerializedName("imdbID")
    private String id;
    @SerializedName("Title")
    private String title;

    @SerializedName("Poster")
    private String poster;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Released")
    private String releaseDate;
    @SerializedName("Runtime")
    private String duration;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Director")
    private String director;
    @SerializedName("Writer")
    private String writer;
    @SerializedName("Actors")
    private String actors;


    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }
}
