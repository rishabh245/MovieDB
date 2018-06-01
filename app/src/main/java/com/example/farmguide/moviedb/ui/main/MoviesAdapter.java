package com.example.farmguide.moviedb.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.db.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context context;
    private List<Movie> movies;
    private MovieClickListener movieClickListener;

    public MoviesAdapter(Context context , MovieClickListener movieClickListener){
        this.context = context;
        this.movieClickListener = movieClickListener;
    }

    public void setMoviesList(List<Movie> movies){
        onNewData(movies);
    }

    private void onNewData(List<Movie> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MovieDiffUtil(newList,movies));
        this.movies = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_layout , parent,false);
        return new MoviesViewHolder(view,movieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
            holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies==null?0:movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView,releaseDateTextView;
        MovieClickListener movieClickListener;
        private Movie movie;

        public MoviesViewHolder(View itemView,MovieClickListener movieClickListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            releaseDateTextView = itemView.findViewById(R.id.text_view_release_date);
            this.movieClickListener = movieClickListener;
            itemView.setOnClickListener(this);
        }


        private void bind(Movie movie) {
            this.movie = movie;
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
        }

        @Override
        public void onClick(View v) {
            movieClickListener.onMoviesClicked(movie);
        }
    }

    public interface MovieClickListener{

        void onMoviesClicked(Movie movie);
    }

    class MovieDiffUtil extends DiffUtil.Callback{

        private List<Movie> newList;
        private List<Movie> oldList;

        MovieDiffUtil(List<Movie> newList , List<Movie> oldList){
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override
        public int getOldListSize() {
           return oldList==null?0:oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList==null?0:newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId()==newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return (newList.get(newItemPosition).getTitle().equals(oldList.get(oldItemPosition).getTitle())
                    && newList.get(newItemPosition).getReleaseDate()
                    .equals(oldList.get(oldItemPosition).getReleaseDate()));
        }
    }
}



