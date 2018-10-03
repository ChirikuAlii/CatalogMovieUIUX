package id.chirikualii.catalogmovieuiux.presentasion.ui.favoritemovie;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.data.db.FavoriteHelper;
import id.chirikualii.catalogmovieuiux.di.component.ActivityComponent;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.OnItemClick;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.RecyclerViewMovieAdapter;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;

public class FavoriteMovieActivity extends AppCompatActivity implements OnItemClick<Movie> {

    @BindView(R.id.recycler_view_favorite)
    RecyclerView recyclerView;
    List<Movie> movieList;

    FavoriteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite_movie);
        helper = new FavoriteHelper(this);
        bindModule();


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
        setupView();
    }

    private void setupView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewMovieAdapter adapter = new RecyclerViewMovieAdapter(movieList,"",this);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        try{
            helper.open();
            movieList = helper.getAllData();

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
        ActivityComponent component = BaseApp.get(getApplicationContext()).getAppComponent().activityComponent().build();
        component.inject(this);
    }


    @Override
    public void onItemClick(Movie item) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_MOVIE_KEY,item);
        startActivity(intent);
    }
}
