package id.chirikualii.catalogmovieuiux.data.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract  {

    public static String TABLE_NAME = "favorite";

    public static class FavoriteColumns implements BaseColumns{
        public static final String FAVORITE_TABLE_NAME = "favorite";
        //public static final String FAVORITE_COLUMN_ID = "_id";
        public static final String FAVORITE_COLUMN_ADULT = "adult";
        public static final String FAVORITE_COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String FAVORITE_COLUMN_GENRES = "genres";
        public static final String FAVORITE_COLUMN_ID_MOVIE = "id_movie";
        public static final String FAVORITE_COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String FAVORITE_COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String FAVORITE_COLUMN_OVERVIEW = "overview";
        public static final String FAVORITE_COLUMN_POPULARITY = "popularity";
        public static final String FAVORITE_COLUMN_POSTER_PATH = "poster_path";
        public static final String FAVORITE_COLUMN_RELEASE_DATE = "release_date";
        public static final String FAVORITE_COLUMN_TITLE = "title";
        public static final String FAVORITE_COLUMN_VIDEO = "video";
        public static final String FAVORITE_COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String FAVORITE_COLUMN_VOTE_COUNT = "vote_count";
        public static final String FAVORITE_COLUMN_IS_FAVORITE = "is_favorite";
    }

    public static final String AUTHORITY = "id.chirikualii.catalogmovieuiux";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}
