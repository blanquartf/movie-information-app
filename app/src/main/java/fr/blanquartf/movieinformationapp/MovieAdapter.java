package fr.blanquartf.movieinformationapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blanquartf on 14/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvMovieTitle) TextView tvMovieTitle;
        @BindView(R.id.ivMoviePoster) ImageView ivMoviePoster;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    public MovieAdapter(List<Movie> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = mDataset.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        Context context =holder.itemView.getContext();
        Picasso.with(context)
                .load(movie.getPoster())
                .error(context.getResources().getDrawable(R.drawable.default_poster))
                .fit().into(holder.ivMoviePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MovieOpenDetailEvent(movie));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
