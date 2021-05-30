package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DisplayEditMovieList extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<MovieModel> courseModalArrayList;
    private DBHandler dbHandler;
    private MovieRVAdapter movieRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_display);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(DisplayEditMovieList.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readFavCourses();

        // on below line passing our array lost to our adapter class.
        movieRVAdapter = new MovieRVAdapter(courseModalArrayList, DisplayEditMovieList.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayEditMovieList.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(movieRVAdapter);
    }
}

