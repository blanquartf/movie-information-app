package fr.blanquartf.movieinformationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieSearchActivity extends AppCompatActivity {

    private static int MOVIES_PER_LINE = 2;
    @BindView(R.id.etMovieName)
    EditText etMovieName;

    @BindView(R.id.rvMovieList) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        ButterKnife.bind(this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,MOVIES_PER_LINE);

        mRecyclerView.setLayoutManager(mLayoutManager);
        ((MovieInformationApplication)getApplication()).getAppComponent().inject(this);
    }

    @OnEditorAction(R.id.etMovieName)
    public boolean searchMovies(TextView textView, int actionId, KeyEvent event) {
        if (actionId != EditorInfo.IME_ACTION_SEARCH) {
            return false;
        }
        Single<MovieSearchResponse> call = service.listMoviesByTitle(((EditText)textView).getText().toString());
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResponse-> {
                    mAdapter = new MovieAdapter(searchResponse.getMovieList());
                    mRecyclerView.setAdapter(mAdapter);
                },error-> Log.e("MovieDetail",error.getMessage()));
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openDetailScreen(MovieOpenDetailEvent event) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.PARAM_MOVIE_ID,event.getMovie().getId());
        startActivity(intent);
    }
}
