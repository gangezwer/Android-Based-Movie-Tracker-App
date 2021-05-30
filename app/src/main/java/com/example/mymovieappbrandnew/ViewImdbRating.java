package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ViewImdbRating extends AppCompatActivity {
    DBHandler dbHandler;
    static String MY_API_KEY = "k_xbl4zltk";

    String movieName;
    private static Button btn;
    String topresultID;
    String topResultImageID;
    ArrayList<String> imagesUrls = new ArrayList<>();
    ArrayList<String>movieRating = new ArrayList<>();
    ListAdapter listAdapter;

    ImageView bmimageView2;
     ListView stextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_imdb_rating);

        dbHandler = new DBHandler(ViewImdbRating.this);

        stextView3 = findViewById(R.id.listViewselected);

        movieName = getIntent().getStringExtra("name");
        System.out.println("Getting posistion of movie after allah-"+movieName);

        /*stextView3.setText(movieName);*/

        new Thread(new CocktailsNamesRunnable(movieName)).start();
    }

    class CocktailsNamesRunnable implements Runnable {
        String ingredient_requested;

        CocktailsNamesRunnable(String ingredient) {
            ingredient_requested = ingredient;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");  // contains all json

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

                /* do the JSON parsing */
                JSONObject json = new JSONObject(stb.toString());
                System.out.println(json);
                JSONArray jsonArray = json.getJSONArray("results");

               /* String[]splitStrings = movieName.split("");
                String movieNameSplit = splitStrings[0];
                String movieYearSplit = splitStrings[1];*/

                for (int i =0;i<jsonArray.length();i++) {
                    //for (int i =0;i<jsonArray.length();i++) {

                    JSONObject topResult = jsonArray.getJSONObject(i);

                    topresultID = topResult.getString("id");
                    String title = topResult.getString("title");
                    String description = topResult.getString("description");
                    imagesUrls.add(topResult.getString("image"));



                        URL ratingUrl = new URL("https://imdb-api.com/en/API/UserRatings/" +
                                MY_API_KEY+"/"+topresultID);
                        HttpURLConnection bon = (HttpURLConnection) ratingUrl.openConnection();

                        BufferedReader bf1 = new BufferedReader(new InputStreamReader(bon.getInputStream()));
                        // read all lines info in a stringbuilder
                        StringBuilder stb2 = new StringBuilder("");  // contains all drink names
                        String lines;
                        while ((lines = bf1.readLine()) != null) {
                            stb2.append(lines);


                        JSONObject json2 = new JSONObject(stb2.toString());
                        String totalRating = json2.getString("totalRating");

                        String listElement = "Title -"+title+" Rating -"+totalRating;

                        movieRating.add(listElement);
                    }
                }


                /*System.out.println("image link -"+topResultImageID);


                URL ratingUrl = new URL("https://imdb-api.com/en/API/Ratings/" +
                        MY_API_KEY+"/"+topresultID);
                HttpURLConnection bon = (HttpURLConnection) ratingUrl.openConnection();

                BufferedReader bf1 = new BufferedReader(new InputStreamReader(bon.getInputStream()));
                // read all lines info in a stringbuilder
                String lines;
                while ((lines = bf1.readLine()) != null) {
                    stb2.append(lines);
                }

                JSONObject json2 = new JSONObject(stb2.toString());
                totalRating = json2.getString("imDb");


                System.out.println("Total rating is - "+totalRating);*/

              /*  // find the cocktail names entries and put them in stb2
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_drink = jsonArray.getJSONObject(i);
                    String cocktail_name = json_drink.getString("strDrink");
                    stb2.append(cocktail_name + "\n");
                }*/

            } catch (Exception ex) {
                ex.printStackTrace();
            }

          /*  Bitmap movie_picture = getBitmap();*/
            /* update the textview with the names of cocktails containing
               the ingredient entered by the user
             */
            runOnUiThread(() -> {

                listAdapter = new ArrayAdapter<String>(ViewImdbRating.this, android.R.layout.simple_selectable_list_item,movieRating);
                stextView3.setAdapter(listAdapter);


                stextView3.setOnItemClickListener((parent, view, position, id) -> {
                    String selectedImage = imagesUrls.get(position);
                    System.out.println(selectedImage);

                    Intent i = new Intent(ViewImdbRating.this, ImdbImageView.class);
                    i.putExtra("imageURL", selectedImage);
                    startActivity(i);

                });

                /*stextView3.setText(totalRating);*/
                /* tv.setText(stb2.toString());*/
                /*System.out.println("total Rating from user - " + totalRating);*/
                /*bmimageView2.setImageBitmap(movie_picture);*/
            });
        }

      /*  // retrieve a bitmap image from the URL in JSON
        Bitmap getBitmap() {
            Bitmap bitmap = null;
            HttpsURLConnection con = null;
            BufferedInputStream bfstream = null;

            try {
                URL url = new URL(topResultImageID);
                con = (HttpsURLConnection) url.openConnection();
                bfstream = new BufferedInputStream(con.getInputStream());

                bitmap = BitmapFactory.decodeStream(bfstream);

            }
            catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                if (con != null) {
                    con.disconnect();
                }
                if (bfstream!=null) {
                    try {
                        bfstream.close();
                    }catch (Exception ee){
                        System.out.println("Unknown error..");
                    }
                }
            }
            return bitmap;

        }*/
    }

}