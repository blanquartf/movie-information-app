package fr.blanquartf.movieinformationapp;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by blanquartf on 14/12/2017.
 */

public interface MovieService {

    @GET("?type=movie")
    Single<MovieSearchResponse> listMoviesByTitle(@Query("s") String searchQuery);

    @GET("?type=movie")
    Single<Movie> getMovie(@Query("i") String id);
}
