package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMovie extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText movieNameEdt, movieYearEdt, movieDirectorEdt, movieActorsEdt, movieReviewEdt;
    private RatingBar movieRatingEdt;
    private TextView favstatus;
    private Button updateCourseBtn;
    private Switch mswitchfav;
    private DBHandler dbHandler;

    String movieName, movieDirector, movieActors, movieReview,movieFavStatus;
    String movieRating;
    String movieYear;
    float ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        // initializing all our variables.

        movieNameEdt = findViewById(R.id.idEdtMovieName);
        movieYearEdt = findViewById(R.id.idEdtMovieYear);
        movieDirectorEdt = findViewById(R.id.idEdtMovieDirector);
        movieActorsEdt = findViewById(R.id.idEdtMovieActors);
        movieRatingEdt = findViewById(R.id.idEdtMovieRating);
        LayerDrawable stars = (LayerDrawable) movieRatingEdt.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        mswitchfav = findViewById(R.id.switchfav);
        movieReviewEdt = findViewById(R.id.idEdtMovieReview);
        favstatus = findViewById(R.id.favstatusgiver);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateMovie.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        movieName = getIntent().getStringExtra("name");
        movieYear = getIntent().getStringExtra("year");
        movieDirector = getIntent().getStringExtra("director");
        movieActors = getIntent().getStringExtra("actors");
        movieRating = getIntent().getStringExtra("rating");
        movieReview = getIntent().getStringExtra("review");
        movieFavStatus = getIntent().getStringExtra("favStatus");

        if (movieFavStatus.equals("0")){
            mswitchfav.setChecked(false);
        }else{
            mswitchfav.setChecked(true);
        }

        // setting data to edit text
        // of our update activity.
        movieNameEdt.setText(movieName);
        movieYearEdt.setText(movieYear);
        movieDirectorEdt.setText(movieDirector);
        movieActorsEdt.setText(movieActors);
        movieRatingEdt.setRating(Integer.parseInt(movieRating));
        movieReviewEdt.setText(movieReview);
        movieYearEdt.setText(movieYear);

        // Called when the user swipes the RatingBar

        if (mswitchfav.isChecked()){
            favstatus.setText("Favorite");

        }else{
            favstatus.setText("Not Favorite");
        }

        mswitchfav.setOnClickListener(v1 -> {
            if (mswitchfav.isChecked()){
                favstatus.setText("Favorite");

            }else{
                favstatus.setText("Not Favorite");
            }
        });


        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(v -> {

            int yearTemp = Integer.parseInt(movieYearEdt.getText().toString());

            int finalRating = (int) movieRatingEdt.getRating();

            if (yearTemp < 1895){
                Toast.makeText(UpdateMovie.this, "Please enter a year after 1895..", Toast.LENGTH_SHORT).show();
                return;
            }

            if (movieNameEdt.getText().toString().isEmpty() && movieDirectorEdt.getText().toString().isEmpty() && movieActorsEdt.getText().toString().isEmpty()  && movieReviewEdt.getText().toString().isEmpty() ) {
                Toast.makeText(UpdateMovie.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            if (movieNameEdt.getText().toString().isEmpty() || movieDirectorEdt.getText().toString().isEmpty() || movieActorsEdt.getText().toString().isEmpty()  || movieReviewEdt.getText().toString().isEmpty() ) {
                Toast.makeText(UpdateMovie.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            String movieTempFav;

            if (mswitchfav.isChecked()){
                movieTempFav = "1";
            }else{
                movieTempFav = "0";
            }

            // inside this method we are calling an update course
            // method and passing all our edit text values.
            dbHandler.updateCourses(movieName, movieNameEdt.getText().toString(),yearTemp, movieDirectorEdt.getText().toString(), movieActorsEdt.getText().toString(),finalRating,movieReviewEdt.getText().toString(),movieTempFav);

            // displaying a toast message that our course has been updated.
            Toast.makeText(UpdateMovie.this, "Movie Details Updated Successfully!!", Toast.LENGTH_SHORT).show();

            // launching our main activity.
            Intent i = new Intent(UpdateMovie.this, MainActivity.class);
            startActivity(i);
        });
    }

}
