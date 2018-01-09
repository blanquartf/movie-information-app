package fr.blanquartf.movieinformationapp;

import android.app.Application;

import fr.blanquartf.movieinformationapp.dagger.AppComponent;
import fr.blanquartf.movieinformationapp.dagger.DaggerAppComponent;
import fr.blanquartf.movieinformationapp.dagger.ServicesModule;

/**
 * Created by blanquartf on 14/12/2017.
 */

public class MovieInformationApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .servicesModule(new ServicesModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
