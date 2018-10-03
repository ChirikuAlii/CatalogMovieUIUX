package id.chirikualii.catalogmovieuiux.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.amitshekhar.utils.DatabaseHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.concurrent.ExecutionException;

import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.data.db.FavoriteHelper;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private int mAppWidgetId;
    private List<Movie> listDetailMovie;
    private FavoriteHelper databaseHelper;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
        );
    }

    @Override
    public void onCreate() {
        /** nothing to do in here */
        databaseHelper = new FavoriteHelper(context);
        databaseHelper.open();
    }

    @Override
    public void onDataSetChanged() {
        listDetailMovie = databaseHelper.getAllData();
    }

    @Override
    public void onDestroy() {
        databaseHelper.close();
    }

    @Override
    public int getCount() {
        return listDetailMovie.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "position: " + position);
        Bitmap bitmap = null;
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image);

        String posterPath = listDetailMovie.get(position).getUrlImagePoster();
        try {
            bitmap = Glide.with(context).asBitmap()
                    .load("http://image.tmdb.org/t/p/w185"+ posterPath)
                    .apply(options)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();


        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        RemoteViews remoteViewsItem = new RemoteViews(
                context.getPackageName(),
                R.layout.widget_favorite_movie_item
        );
        remoteViewsItem.setImageViewBitmap(
                R.id.image_view_poster_favorite_movie_widget_item,
                bitmap
        );
        remoteViewsItem.setTextViewText(
                R.id.txt_tanggal_favorite_movie_widget_item,
                listDetailMovie.get(position).getReleaseDate()
        );

        Bundle bundle = new Bundle();
        bundle.putInt(FavoriteMovieWidget.EXTRA_ITEM, position);
        //bundle.putParcelable(FavoriteMovieWidget.EXTRA_ITEM,listDetailMovie.get(position));
        //bundle.putString(FavoriteMovieWidget.EXTRA_ITEM, listDetailMovie.get(position).getIdMovie());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(bundle);
        remoteViewsItem.setOnClickFillInIntent(
                R.id.image_view_poster_favorite_movie_widget_item,
                fillInIntent
        );
        return remoteViewsItem;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
