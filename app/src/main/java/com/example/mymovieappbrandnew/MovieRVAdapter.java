package com.example.mymovieappbrandnew;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieRVAdapter extends RecyclerView.Adapter<MovieRVAdapter.ViewHolder>{
    // variable for our array list and context
    private ArrayList<MovieModel> courseModalArrayList;
    private Context context;

    // constructor
    public MovieRVAdapter(ArrayList<MovieModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_rv_item, parent, false);
        return new MovieRVAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        MovieModel modal = courseModalArrayList.get(position);
        holder.movieNameTV.setText(modal.getName());
        holder.movieYearTV.setText(Integer.toString(modal.getYear()));
        holder.movieDirectorTV.setText(modal.getDirector());
        holder.movieRating.setText(Integer.toString(modal.getRating()));

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(v -> {

            // on below line we are calling an intent.
            Intent i = new Intent(context, UpdateMovie.class);

            // below we are passing all our values.
            i.putExtra("name", modal.getName());
            i.putExtra("year", Integer.toString(modal.getYear()));
            i.putExtra("director", modal.getDirector());
            i.putExtra("actors", modal.getActors());
            i.putExtra("rating", Integer.toString(modal.getRating()));
            i.putExtra("review", modal.getReview());
            i.putExtra("favStatus", modal.getFav_status());

            // starting our activity.
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView movieNameTV, movieYearTV, movieDirectorTV,movieRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            movieNameTV = itemView.findViewById(R.id.idTVtMovieName);
            movieYearTV = itemView.findViewById(R.id.idTVtMovieYear);
            movieDirectorTV = itemView.findViewById(R.id.idTVtMovieDirector);
            movieRating = itemView.findViewById(R.id.idTVtMovieRating);
        }
    }
}