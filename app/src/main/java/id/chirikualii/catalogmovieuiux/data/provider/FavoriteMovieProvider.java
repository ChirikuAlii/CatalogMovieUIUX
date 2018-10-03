package id.chirikualii.catalogmovieuiux.data.provider;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.data.db.DbContract;
import id.chirikualii.catalogmovieuiux.data.db.DbHelper;
import id.chirikualii.catalogmovieuiux.data.db.FavoriteHelper;
import id.chirikualii.catalogmovieuiux.di.ApplicationContext;
import id.chirikualii.catalogmovieuiux.di.component.ActivityComponent;
import id.chirikualii.catalogmovieuiux.di.component.ProviderComponent;
import id.chirikualii.catalogmovieuiux.di.scope.ProviderScope;


public class FavoriteMovieProvider extends ContentProvider {

    private final String TAG = getClass().getSimpleName();
    private static final String AUTHORITY = "id.chirikualii.catalogmovieuiux";
    private static final String BASE_PATH = "favorite";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID_MOVIE = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase sqLiteDatabase;

    private FavoriteHelper favoriteHelper;

    public FavoriteMovieProvider() {

    }

    @Inject
    public FavoriteMovieProvider(FavoriteHelper favoriteHelper, @ApplicationContext Context context) {
        this.favoriteHelper = favoriteHelper;

    }

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, FAVORITE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FAVORITE_ID_MOVIE);
    }

    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        favoriteHelper.open();
        sqLiteDatabase = favoriteHelper.getDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        injectActivity();
        Cursor cursor = null;
        if (uriMatcher.match(uri) == FAVORITE) {
            cursor = favoriteHelper.getAllProvider();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        injectActivity();
        long id = favoriteHelper.insertDataFavoriteProvider(contentValues);
        if (id > 0) {
            Uri mUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver()
                    .notifyChange(uri, null);
            return mUri;
        }
        throw new SQLException("Insertion Failed for URI: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        injectActivity();
        int deletedCount = 0;
        switch (uriMatcher.match(uri)) {
            case FAVORITE:
                deletedCount = sqLiteDatabase.delete(DbContract.FavoriteColumns.FAVORITE_TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        injectActivity();
        /*int updatedCount = 0;
        switch (uriMatcher.match(uri)) {
            case FAVORITE:
                updatedCount = sqLiteDatabase.update(
                        DatabaseHelper.FAVORITE_TABLE_NAME,
                        contentValues,
                        s,
                        strings
                );
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updatedCount;*/

        return 0;
    }

    private void injectActivity() {
        ProviderComponent component = BaseApp.get(getContext()).getAppComponent().providerComponent().build();
        component.inject(this);
    }
}
