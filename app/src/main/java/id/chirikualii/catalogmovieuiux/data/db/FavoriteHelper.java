package id.chirikualii.catalogmovieuiux.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.chirikualii.catalogmovieuiux.di.ApplicationContext;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;


public class FavoriteHelper {
    private static String TABLE_NAME = DbContract.FavoriteColumns.FAVORITE_TABLE_NAME;

    Context context;

    public FavoriteHelper(Context context) {
        this.context = context;
    }

    DbHelper helper;
    SQLiteDatabase database;

    public SQLiteDatabase getDatabase() {
        return helper.getWritableDatabase();
    }




    public FavoriteHelper open () throws SQLException{
        helper = new DbHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }
    public void close(){
        helper.close();
    }

    //query getAllData
    public Cursor queryAllData(){
        return database.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + DbContract.FavoriteColumns._ID + " ASC",null);
    }

    public ArrayList<Movie> getAllData(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Cursor cursor = queryAllData();

        cursor.moveToFirst();
        if( cursor.getCount()>0){
            do{
                Movie movie = new Movie();
                movie.setOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_ORIGINAL_TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_RELEASE_DATE)));
                movie.setUrlImageBackdrop(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_BACKDROP_PATH)));
                movie.setUrlImagePoster(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_POSTER_PATH)));
                movie.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_VOTE_AVERAGE)));
                movie.setIdMovie(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE)));
                movie.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.FavoriteColumns.FAVORITE_COLUMN_IS_FAVORITE)));
                movieArrayList.add(movie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return movieArrayList;
    }

    public Cursor getAllProvider() {
        return database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public long insert(Movie movie){
        ContentValues initialValues = new ContentValues();

        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_ORIGINAL_TITLE,movie.getOriginalTitle());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_RELEASE_DATE,movie.getReleaseDate());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_OVERVIEW,movie.getOverview());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_BACKDROP_PATH,movie.getUrlImageBackdrop());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_POSTER_PATH,movie.getUrlImagePoster());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_VOTE_AVERAGE,movie.getVoteAverage());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE,movie.getIdMovie());
        initialValues.put(DbContract.FavoriteColumns.FAVORITE_COLUMN_IS_FAVORITE,movie.getFavorite());
        //insertDataFavoriteProvider(initialValues);
        return database.insert(TABLE_NAME,null,initialValues);
    }

    public long insertDataFavoriteProvider(ContentValues contentValues) {

        return database.insert(TABLE_NAME, null, contentValues);
    }


    public long delete(Movie movie){
        //deleteDataFavoriteProvider(movie.getIdMovie());
        return database.delete(
                TABLE_NAME,
                DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE + " = ?",
                new String[]{movie.getIdMovie()});

    }
    public int deleteDataFavoriteProvider(String idMovie) {

        return database.delete(TABLE_NAME,
                DbContract.FavoriteColumns._ID + " = ?",
                new String[]{String.valueOf(idMovie)}
        );
    }

    public boolean itemDataAlreadyAdded(String idMovie) {
        boolean isDataAlreadyAdded;

        Cursor cursor = database.rawQuery(
                "SELECT * FROM " + TABLE_NAME
                        + " WHERE "
                        + DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE + " = ?",
                new String[]{String.valueOf(idMovie)},
                null
        );
        isDataAlreadyAdded = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return isDataAlreadyAdded;
    }

    public boolean itemDataAlreadyAddedProvider(long idMovie) {
        boolean isDataAlreadyAdded;

        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                DbContract.FavoriteColumns._ID+ " = ?",
                new String[]{String.valueOf(idMovie)},
                null,
                null,
                null
        );
        isDataAlreadyAdded = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return isDataAlreadyAdded;
    }


}
