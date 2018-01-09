package fr.blanquartf.movieinformationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by blanquartf on 15/12/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    public static final String PARAM_MOVIE_ID="PARAM_MOVIE_ID";

    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.tvMovieReleasedDate)
    TextView tvMovieReleasedDate;
    @BindView(R.id.tvMovieGenre)
    TextView tvMovieGenre;
    @BindView(R.id.tvMovieTime)
    TextView tvMovieTime;
    @BindView(R.id.ivMoviePoster)
    ImageView ivMoviePoster;
    @BindView(R.id.tvMoviePlot)
    TextView tvMoviePlot;
    @BindView(R.id.tvMovieDirector)
    TextView tvMovieDirector;
    @BindView(R.id.tvWriter)
    TextView tvWriter;
    @BindView(R.id.tvActors)
    TextView tvActors;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        ((MovieInformationApplication)getApplication()).getAppComponent().inject(this);
        loadData();
    }

    private void loadData()
    {
        Intent intent = getIntent();
        service.getMovie(intent.getStringExtra(PARAM_MOVIE_ID)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie->bindMovie(movie),error-> Log.e("MovieDetail",error.getMessage()));
    }

    private void bindMovie(Movie movie)
    {
        tvMovieTitle.setText(movie.getTitle());
        tvMovieGenre.setText(movie.getGenre());
        tvMovieReleasedDate.setText(movie.getReleaseDate());
        tvMovieTime.setText(movie.getDuration());
        tvMovieDirector.setText(movie.getDirector());
        tvMoviePlot.setText(movie.getPlot());
        tvWriter.setText(movie.getWriter());
        tvActors.setText(movie.getActors());
        Picasso.with(this)
                .load(movie.getPoster())
                .error(getResources().getDrawable(R.drawable.default_poster))
                .fit().into(ivMoviePoster);

    }
}
