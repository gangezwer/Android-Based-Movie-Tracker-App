package com.example.mymovieappbrandnew;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  RegisterMovie extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler
    private EditText movieNameEdt, movieYearEdt, movieDirectorEdt, movieActorsEdt, movieRatingEdt, movieReviewEdt;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        // initializing all our variables.
        movieNameEdt = findViewById(R.id.idEdtMovieName);
        movieYearEdt = findViewById(R.id.idEdtMovieYear);
        movieDirectorEdt = findViewById(R.id.idEdtMovieDirector);
        movieActorsEdt = findViewById(R.id.idEdtMovieActors);
        movieRatingEdt = findViewById(R.id.idEdtMovieRating);
        movieReviewEdt = findViewById(R.id.idEdtMovieReview);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(RegisterMovie.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int movieYear = 0;
                int movieRating =0;
                // below line is to get data from all edit text fields.
                String movieName = movieNameEdt.getText().toString();
                try {
                    movieYear = Integer.parseInt(movieYearEdt.getText().toString());
                    movieRating = Integer.parseInt(movieRatingEdt.getText().toString());
                }catch (NumberFormatException ee){}
                String movieDirector = movieDirectorEdt.getText().toString();
                String movieActors = movieActorsEdt.getText().toString();
                String movieReview = movieReviewEdt.getText().toString();
                String movieFavStatus = "0";

                // validating if the text fields are empty or not.
                if (movieName.isEmpty() && movieDirector.isEmpty() && movieActors.isEmpty()  && movieReview.isEmpty() ) {
                    Toast.makeText(RegisterMovie.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (movieName.isEmpty() || movieDirector.isEmpty() || movieActors.isEmpty() || movieReview.isEmpty() ) {
                    Toast.makeText(RegisterMovie.this, "Please enter valid data for all..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (movieYear <= 1895){
                    Toast.makeText(RegisterMovie.this, "Please enter a year after 1895..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (movieYear > 9999){
                    Toast.makeText(RegisterMovie.this, "Please enter a valid year with 4 digits..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (movieRating < 1 || movieRating > 10){
                    Toast.makeText(RegisterMovie.this, "Please enter a rating between 1 - 10..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(movieName, movieYear, movieDirector, movieActors, movieRating, movieReview,movieFavStatus);

                // after adding the data we are displaying a toast message.
                Toast.makeText(RegisterMovie.this, "Movie has been added successfully..", Toast.LENGTH_SHORT).show();
                movieNameEdt.setText("");
                movieYearEdt.setText("");
                movieDirectorEdt.setText("");
                movieActorsEdt.setText("");
                movieRatingEdt.setText("");
                movieReviewEdt.setText("");
            }
        });
    }
}