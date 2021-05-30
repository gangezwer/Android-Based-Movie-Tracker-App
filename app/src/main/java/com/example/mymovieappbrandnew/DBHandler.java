package com.example.mymovieappbrandnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    public static final String DB_NAME = "mymovies";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    public static final String TABLE_NAME = "mymovies";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    public static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String YEAR_COL = "year";

    // below variable for our course description column.
    public static final String DIRECTOR_COL = "director";

    // below variable is for our course tracks column.
    public static final String ACTORS_COL = "actors";

    // below variable is for our course tracks column.
    private static final String RATING_COL = "rating";


    // below variable is for our course tracks column.
    private static final String REVIEW_COL = "review";

    // below variable is for our course tracks column.
    public static final String FAVSTATUS_COL = "favStatus";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + YEAR_COL + " INT ,"
                + DIRECTOR_COL + " TEXT,"
                + ACTORS_COL + " TEXT,"
                + RATING_COL + " INT ,"
                + REVIEW_COL + " TEXT,"
                + FAVSTATUS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String name, int year, String director, String actors, int rating, String review, String fav_status) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(YEAR_COL, year);
        values.put(DIRECTOR_COL, director);
        values.put(ACTORS_COL, actors);
        values.put(RATING_COL, rating);
        values.put(REVIEW_COL, review);
        values.put(FAVSTATUS_COL, fav_status);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<String> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<MovieModel> courseModalArrayList = new ArrayList<>();
        ArrayList<String> favArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new MovieModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
            for (MovieModel movieModel:courseModalArrayList){
                if (movieModel.getFav_status().equals("1")){
                    favArrayList.add(movieModel.getName());
                }
            }
        }

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return favArrayList;
    }

    // we have created a new method for reading all the courses.
    public ArrayList<String> readNameYear() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<MovieModel> courseModalArrayList = new ArrayList<>();
        ArrayList<String> favArrayList = new ArrayList<>();
        String concatenatedStrigng ;

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new MovieModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
            for (MovieModel movieModel:courseModalArrayList){
                    String name =movieModel.getName();
                    String year = String.valueOf(movieModel.getYear());

                    concatenatedStrigng = name+" "+year;

                    favArrayList.add(concatenatedStrigng);
            }
        }

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return favArrayList;
    }


    public ArrayList<String> readStringCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<MovieModel> courseModalArrayList = new ArrayList<>();
        ArrayList<String> favArrayList = new ArrayList<>();
        String concatenatedStrigng ;

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new MovieModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
            for (MovieModel movieModel:courseModalArrayList){

                String name =movieModel.getName();
                String director = movieModel.getDirector();
                String actors = movieModel.getActors();

                concatenatedStrigng =  name + ", " + director+ ", " + actors;

                    favArrayList.add(concatenatedStrigng.toLowerCase());
            }
        }

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return favArrayList;
    }

    /*// below is the method for updating our courses
    public void updateCourse(String originalCourseName, String movieName, int movieYear, String movieDirector, String movieActors, int movieRating, String movieReview, String movieFavStatus) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, movieName);
        values.put(YEAR_COL, movieYear);
        values.put(DIRECTOR_COL, movieDirector);
        values.put(ACTORS_COL, movieActors);
        values.put(RATING_COL, movieRating);
        values.put(REVIEW_COL, movieReview);
        values.put(FAVSTATUS_COL, movieFavStatus);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }*/

    public void updateCourses(String originalCourseName, String movieName, int movieYear, String movieDirector, String movieActors, int movieRating, String movieReview,String movieFavStatus) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, movieName);
        values.put(YEAR_COL, movieYear);
        values.put(DIRECTOR_COL, movieDirector);
        values.put(ACTORS_COL, movieActors);
        values.put(RATING_COL, movieRating);
        values.put(REVIEW_COL, movieReview);
        values.put(FAVSTATUS_COL,movieFavStatus);
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<MovieModel> readFavCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<MovieModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new MovieModel(cursorCourses.getString(1),
                        cursorCourses.getInt(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from mymovies", null);
        //"Select movie name from database name where favorite status =?",new String[]{"1"}
        return cursor;
    }

    /*public Cursor getFavData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select name from mymovies Where favStatus =?",new String[]{"1"});
        return cursor;
    }


    public Cursor searchData(String text){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from"+TABLE_NAME+" WHERE "+NAME_COL+"Like %"+text+"%'";

        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }*/


}