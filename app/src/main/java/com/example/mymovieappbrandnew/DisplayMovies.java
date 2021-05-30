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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.mymovieappbrandnew.DBHandler.DB_NAME;
import static com.example.mymovieappbrandnew.DBHandler.FAVSTATUS_COL;
import static com.example.mymovieappbrandnew.DBHandler.NAME_COL;
import static com.example.mymovieappbrandnew.DBHandler.TABLE_NAME;

public class DisplayMovies extends AppCompatActivity {
        DBHandler dbHandler;
        ListView listView;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void get(){
            Cursor cursor = dbHandler.getData();
            ArrayList<String> movies = new ArrayList<>();

            while (cursor.moveToNext()){
                movies.add(cursor.getString(1));
            }
            //sorting the name in alphabetical order
            movies.sort(new NameComparator());
            ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,movies);

            listView.setAdapter(listAdapter);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_movies);

            dbHandler = new DBHandler(this);

            listView = findViewById(R.id.listView);

            get();
        }

    public void submitToFav(View view) {
        for(int i=0; i<listView.getCount();i++){
            SparseBooleanArray selectMovie=listView.getCheckedItemPositions();
            if(selectMovie.get(i)) {
                String movieName = (String) listView.getItemAtPosition(i);
                System.out.println(movieName);
                add(movieName);
            }
        }
    }

    public void add(String name){
        SQLiteDatabase database = dbHandler.getReadableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(FAVSTATUS_COL,"1");
        database.update(TABLE_NAME,contentValues,NAME_COL+" =?",new String[]{name});
        database.close();
    }
}