package fr.blanquartf.movieinformationapp;

/**
 * Created by blanquartf on 15/12/2017.
 */

class MovieOpenDetailEvent {

    private Movie movie;

    public MovieOpenDetailEvent(Movie movie) {
        this.movie=movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
