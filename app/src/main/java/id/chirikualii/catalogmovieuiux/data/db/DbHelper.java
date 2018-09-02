package id.chirikualii.catalogmovieuiux.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.chirikualii.catalogmovieuiux.di.ApplicationContext;
import id.chirikualii.catalogmovieuiux.di.DatabaseInfo;


import static id.chirikualii.catalogmovieuiux.data.db.DbContract.FavoriteColumns.FAVORITE_TABLE_NAME;
@Singleton
public class DbHelper extends SQLiteOpenHelper{

    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String name,
                    @DatabaseInfo int version) {
        super(context, name, null, version);
    }
    public static String CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE %s " + "(" +
                    " %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s BOOLEAN," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s BOOLEAN," +
                    " %s DOUBLE," +
                    " %s LONG," +
                    " %s INTEGER)",
            DbContract.FavoriteColumns.FAVORITE_TABLE_NAME,
            DbContract.FavoriteColumns._ID,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_ADULT,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_BACKDROP_PATH,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_GENRES,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_ID_MOVIE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_ORIGINAL_LANGUAGE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_ORIGINAL_TITLE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_OVERVIEW,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_POPULARITY,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_POSTER_PATH,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_RELEASE_DATE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_TITLE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_VIDEO,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_VOTE_AVERAGE,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_VOTE_COUNT,
            DbContract.FavoriteColumns.FAVORITE_COLUMN_IS_FAVORITE
    );
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITE_TABLE_NAME);
    }
    
}
