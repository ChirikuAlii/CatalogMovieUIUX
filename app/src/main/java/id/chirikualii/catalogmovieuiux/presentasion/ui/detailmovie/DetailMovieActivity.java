package id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String DETAIL_MOVIE_KEY = "detail movie";

    @BindView(R.id.txt_title_movie) TextView txtTitle;
    @BindView(R.id.txt_release_date) TextView txtReleaseDate;
    @BindView(R.id.txt_vote_average) TextView txtVoteAverage;
    @BindView(R.id.txt_overview) TextView txtOverview;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        this.bindModule();
        Movie movie = (Movie) getIntent().getSerializableExtra(DETAIL_MOVIE_KEY);

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
    }

    private void bindModule() {
        ButterKnife.bind(this);
    }
}
