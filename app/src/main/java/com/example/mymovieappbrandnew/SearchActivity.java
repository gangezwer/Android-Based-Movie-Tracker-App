package com.example.mymovieappbrandnew;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import static com.example.mymovieappbrandnew.DBHandler.FAVSTATUS_COL;
import static com.example.mymovieappbrandnew.DBHandler.NAME_COL;
import static com.example.mymovieappbrandnew.DBHandler.TABLE_NAME;


public class SearchActivity extends AppCompatActivity {

    DBHandler dbHandler;
    ListView listView;
    ArrayList<String> favArrayList;
    ArrayList<String> movies;
    Button btnLookUp;
    EditText search;
    String dataEntered;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.listView);
        btnLookUp = findViewById(R.id.button3);
        search = findViewById(R.id.searchBar);

        dbHandler = new DBHandler(this);

        favArrayList = dbHandler.readStringCourses();

        listView = findViewById(R.id.listView);

        movies = new ArrayList<String>();


        System.out.println("entered"+dataEntered);

            btnLookUp.setOnClickListener(v -> {
                if (count >= 1) {
                    movies.clear();
                }

                dataEntered = search.getText().toString().toLowerCase().trim();

                if (dataEntered.isEmpty()){
                    Toast.makeText(SearchActivity.this, "Please type something to look up..", Toast.LENGTH_SHORT).show();
                }

                Boolean found = false;

                for (int i = 0; i < favArrayList.size(); i++) {
                    if ((!dataEntered.isEmpty()) && favArrayList.get(i).toLowerCase().contains(dataEntered)) {
                        movies.add(favArrayList.get(i));
                        found = true;
                    }
                }
               if (!found){
                    Toast.makeText(SearchActivity.this, "No results found..", Toast.LENGTH_SHORT).show();
                }
                get();
                count++;
            });
    }

    private void get() {

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_selectable_list_item,movies);

/*
        ListAdapter listAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_2,movies);
*/

        listView.setAdapter(adapter);

    }

}
