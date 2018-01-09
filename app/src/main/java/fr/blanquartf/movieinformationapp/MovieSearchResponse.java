package fr.blanquartf.movieinformationapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blanquartf on 14/12/2017.
 */

public class MovieSearchResponse {
    @SerializedName("Search")
    List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
