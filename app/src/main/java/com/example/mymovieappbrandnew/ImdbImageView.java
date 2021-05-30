package com.example.mymovieappbrandnew;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImdbImageView extends AppCompatActivity {
    String movieImageURL;

    ImageView mmovieImage;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_image_view);

        movieImageURL = getIntent().getStringExtra("imageURL");

        mmovieImage = findViewById(R.id.movieImage);

        new Thread(new ImdbImageView.CocktailsNamesRunnable(movieImageURL)).start();
    }


    public class CocktailsNamesRunnable implements Runnable {
        String ingredient_requested;

        CocktailsNamesRunnable(String ingredient) {
            ingredient_requested = ingredient;
        }

        @Override
        public void run() {
            StringBuilder stb = new StringBuilder("");  // contains all json

            bitmap = null;
            HttpsURLConnection con = null;
            BufferedInputStream bfstream = null;

            try {
                URL url = new URL(ingredient_requested);
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

            runOnUiThread(() -> {
                mmovieImage.setImageBitmap(bitmap);
            });
        }
    }
}