package id.chirikualii.catalogmovieuiux.presentasion.ui.searchmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.di.component.ActivityComponent;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.OnItemClick;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.RecyclerViewMovieAdapter;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.main.MainActivity;

@ActivityScope
public class SearchMovieActivity extends AppCompatActivity implements SearchMovieView, OnItemClick<Movie> {
    public final static String SEARCH_MOVIE_KEY = "search movie";

    @Inject
    SearchMoviePresenter presenter;
    @BindView(R.id.recycler_view_search)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        bindModule();
        //setup view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String query = getIntent().getStringExtra(SearchMovieActivity.SEARCH_MOVIE_KEY);
        presenter.performLoadMovie(query);
    }

    private void bindModule() {
        ButterKnife.bind(this);
        injectActivity();
        presenter.bind(this);
    }

    private void injectActivity() {
        ActivityComponent component = BaseApp.get(getApplicationContext()).getAppComponent().activityComponent().build();
        component.inject(this);
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        RecyclerViewMovieAdapter adapter =
                new RecyclerViewMovieAdapter(movieList,SearchMovieActivity.SEARCH_MOVIE_KEY,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setOnErrorLoadMovie(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Movie item) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_MOVIE_KEY,item);
        startActivity(intent);
    }
}
