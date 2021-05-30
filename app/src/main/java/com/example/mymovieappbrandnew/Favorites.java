package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.mymovieappbrandnew.DBHandler.FAVSTATUS_COL;
import static com.example.mymovieappbrandnew.DBHandler.NAME_COL;
import static com.example.mymovieappbrandnew.DBHandler.TABLE_NAME;

public class Favorites extends AppCompatActivity {

    DBHandler dbHandler;
    ListView listView;
    ArrayList<String> favArrayList;
    ArrayList<String> movies;

    public void get() {

        favArrayList = new ArrayList<>();

         movies = new ArrayList<String>();

         favArrayList = dbHandler.readCourses();

         ListAdapter listAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_multiple_choice,favArrayList);


        listView.setAdapter(listAdapter);

        for (int x =0;x<=favArrayList.size();x++){
            listView.setItemChecked(x,true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        dbHandler = new DBHandler(this);

        listView = findViewById(R.id.listView);

        get();
    }

    public void submitToFav(View view) {
        SparseBooleanArray selectMovie = listView.getCheckedItemPositions();
        System.out.println(selectMovie);
        for(int i=0; i<favArrayList.size();i++){
            if(!(listView.isItemChecked(i))) {
                String movieName = favArrayList.get(i);
                String movieNames = (String) listView.getItemAtPosition(i);
                System.out.println(movieName);
               add(movieNames);
            }
        }
    }

    public void add(String movieModel){
        SQLiteDatabase database = dbHandler.getReadableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(FAVSTATUS_COL,"0");
        database.update(TABLE_NAME,contentValues,NAME_COL+" =?",new String[]{movieModel});
        database.close();
    }
}