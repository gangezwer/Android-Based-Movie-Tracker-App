package com.example.mymovieappbrandnew;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class IMDBRating extends AppCompatActivity{
    DBHandler dbHandler;
    ListView listView;
    ArrayList<String> favArrayList;
    String movieName;

    public void get() {

        favArrayList = new ArrayList<>();

        favArrayList = dbHandler.readNameYear();

        ListAdapter listAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_single_choice,favArrayList);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
             movieName = (String) listView.getItemAtPosition(position);

            System.out.println("Getting posistion of movie-"+movieName);

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_m_d_b_rating);

        dbHandler = new DBHandler(this);

        listView = findViewById(R.id.listView);

        get();
    }

    /*public void submitToFav(View view) {
        for(int i=0; i<listView.getCount();i++){
            SparseBooleanArray selectMovie=listView.getCheckedItemPositions();
            if(selectMovie.get(i)) {
                String movieName = (String) listView.getItemAtPosition(i);
                System.out.println(movieName);
                add(movieName);
            }
        }
    }*/


    /*public void add(String name){
        SQLiteDatabase database = dbHandler.getReadableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(FAVSTATUS_COL,"1");
        database.update(TABLE_NAME,contentValues,NAME_COL+" =?",new String[]{name});
        database.close();
    }*/

   /* public void openIMDPRatingPage(View view) throws IOException {
        System.out.println(movieName);

       //* url_string = "https://imdb-api.com/en/API/SearchTitle/k_bo426yge/inception%202010";

        URL url = new URL(url_string);
        url.openStream();

        DownloadTask task = new DownloadTask();
        task.execute(url_string);
    }*/

/*
        url_string = "https://imdb-api.com/en/API/SearchTitle/"+ MY_API_KEY +"/"+ movieNameYear;

        URL url = new URL(url_string);
        url.openStream();

        DownloadTask task = new DownloadTask();
        task.execute(url_string);*/
        // on below line we are calling an intent.
        /*Intent i = new Intent(context, ViewImdbRating.class);*/

     /*   // below we are passing all our values.
        i.putExtra("name", movieNameYear);

        // starting our activity.
        context.startActivity(i);*/

    public void getRatingId(){
        /*url_string = "https://imdb-api.com/en/API/SearchTitle/"+ MY_API_KEY +"/"+ movieName;*/
    }

    public void getImdbRating(){
    }

    public void openIMDPRatingPage(View view) throws IOException {
        /*new Thread(new CocktailsNamesRunnable(movieName)).start();*/
        System.out.println("Viewing final movie posistion elekiri -"+ movieName);
        // on below line we are calling an intent.
        Intent i = new Intent(this, ViewImdbRating.class);
        i.putExtra("name", movieName);
        startActivity(i);
        System.out.println("Getting posistion of movie after sendung-"+movieName);
    }

  /*  class CocktailsNamesRunnable implements Runnable {
        String ingredient_requested;

        CocktailsNamesRunnable(String ingredient) {
            ingredient_requested = ingredient;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");  // contains all json
            StringBuilder stb2 = new StringBuilder("");  // contains all drink names

            try {
                // make the connection and receive the input stream
                URL searchUrl = new URL("https://imdb-api.com/en/API/SearchTitle/" +
                        MY_API_KEY+"/"+movieName);
                HttpURLConnection con = (HttpURLConnection) searchUrl.openConnection();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // read all lines info in a stringbuilder
                String line;
                while ((line = bf.readLine()) != null) {
                    stb.append(line);
                }

                *//* do the JSON parsing *//*
                JSONObject json = new JSONObject(stb.toString());
                JSONArray jsonArray = json.getJSONArray("results");

                JSONObject topResult = jsonArray.getJSONObject(0);

                String topresultID = topResult.getString("id");


                URL ratingUrl = new URL("https://imdb-api.com/en/API/UserRatings/" +
                        MY_API_KEY+"/"+topresultID);
                HttpURLConnection bon = (HttpURLConnection) ratingUrl.openConnection();

                BufferedReader bf1 = new BufferedReader(new InputStreamReader(bon.getInputStream()));
                // read all lines info in a stringbuilder
                String lines;
                while ((lines = bf1.readLine()) != null) {
                    stb2.append(lines);
                }

                JSONObject json2 = new JSONObject(stb2.toString());
                totalRating = json2.getString("totalRating");

                System.out.println("Total rating is - "+totalRating);

              *//*  // find the cocktail names entries and put them in stb2
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_drink = jsonArray.getJSONObject(i);
                    String cocktail_name = json_drink.getString("strDrink");
                    stb2.append(cocktail_name + "\n");
                }*//*

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            *//* update the textview with the names of cocktails containing
               the ingredient entered by the user
             *//*
            runOnUiThread(() -> {
               *//* tv.setText(stb2.toString());*//*
            });
        }
    }*/

}