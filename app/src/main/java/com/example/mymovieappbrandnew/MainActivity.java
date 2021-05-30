package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();

        try {
            this.getSupportActionBar().hide();              // remove title bar of app
        } catch (NullPointerException e) {
        }

        Button registerMovies = findViewById(R.id.button7);
        registerMovies.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterMovie.class);
            startActivity(intent);
        });

        Button displsayMovies = findViewById(R.id.button8);
        displsayMovies.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplayMovies.class);
            startActivity(intent);
        });

       Button favorites = findViewById(R.id.button9);
        favorites.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Favorites.class);
            startActivity(intent);
        });

       Button editMovies = findViewById(R.id.button10);
        editMovies.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplayEditMovieList.class);
            startActivity(intent);
        });

        Button searchMovies = findViewById(R.id.button11);
        searchMovies.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        Button imdbRating = findViewById(R.id.button12);
        imdbRating.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IMDBRating.class);
            startActivity(intent);
        });

    }
}