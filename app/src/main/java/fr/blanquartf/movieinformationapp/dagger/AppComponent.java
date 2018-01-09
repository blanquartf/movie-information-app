package fr.blanquartf.movieinformationapp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import fr.blanquartf.movieinformationapp.MovieDetailActivity;
import fr.blanquartf.movieinformationapp.MovieSearchActivity;

/**
 * Created by blanquartf on 27/11/2017.
 */
@Singleton
@Component(modules = {ServicesModule.class})
public interface AppComponent {
    void inject(MovieSearchActivity mainActivity);
    void inject(MovieDetailActivity detailActivity);
}
