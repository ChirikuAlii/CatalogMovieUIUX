package id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie;

import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.data.db.FavoriteHelper;
import id.chirikualii.catalogmovieuiux.di.component.ActivityComponent;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
@ActivityScope
public class DetailMovieActivity extends AppCompatActivity  {

    public static final String DETAIL_MOVIE_KEY = "detail movie";

    @BindView(R.id.txt_title_movie) TextView txtTitle;
    @BindView(R.id.txt_release_date) TextView txtReleaseDate;
    @BindView(R.id.txt_vote_average) TextView txtVoteAverage;
    @BindView(R.id.txt_overview) TextView txtOverview;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @BindView(R.id.fab_fav) FloatingActionButton fabFav;
    boolean isFavorite;
    @Inject
    FavoriteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        this.bindModule();
        Movie movie = (Movie) getIntent().getParcelableExtra(DETAIL_MOVIE_KEY);

        //bind
        txtTitle.setText(movie.getOriginalTitle());
        txtReleaseDate.setText(movie.getReleaseDate());
        txtOverview.setText(movie.getOverview());
        txtVoteAverage.setText(String.valueOf(movie.getVoteAverage()));

        Glide
                .with(this)
                .load("http://image.tmdb.org/t/p/w185"+movie.getUrlImagePoster())
                .into(imgPoster);

        Glide
                .with(this)
                .load("http://image.tmdb.org/t/p/w780"+movie.getUrlImageBackdrop())
                .into(imgBackdrop);

        checkIsAdded(movie);
        if(isFavorite){
            fabFav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite));
            movie.setFavorite(1);
        }else {
            fabFav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border));
            movie.setFavorite(0);
        }

        fabFav.setOnClickListener(view -> {
            if(movie.getFavorite()!=1){
                new InsertDataAsync().execute(movie);
                fabFav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite));

            }else {
                fabFav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border));
                new DeleteDataAsync().execute(movie);

            }
        });
    }

    private void checkIsAdded(Movie movie) {
        try{
            helper.open();
            isFavorite = helper.itemDataAlreadyAdded(movie.getIdMovie());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            helper.close();
        }
    }

    private void bindModule() {
        ButterKnife.bind(this);
        this.injectActivity();
    }

    private void injectActivity() {
        Context context=this.getApplicationContext();
        ActivityComponent injector =((BaseApp)context).getAppComponent().activityComponent().build();
        injector.inject(this);
    }


    class InsertDataAsync extends AsyncTask<Movie,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Movie... movies) {
            try {
                helper.open();
                movies[0].setFavorite(1);
                helper.insert(movies[0]);
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                helper.close();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(DetailMovieActivity.this, "Data tersimpan", Toast.LENGTH_SHORT).show();

        }
    }
    class DeleteDataAsync extends AsyncTask<Movie,Void,Void>{

        @Override
        protected Void doInBackground(Movie... movies) {
            try{
                helper.open();
                movies[0].setFavorite(0);
                helper.delete(movies[0]);
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                helper.close();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(DetailMovieActivity.this, "Data terhapus", Toast.LENGTH_SHORT).show();
        }
    }
}
