package id.chirikualii.catalogmovieuiux.presentasion.ui.nowplaying;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.di.component.FragmentComponent;
import id.chirikualii.catalogmovieuiux.di.scope.FragmentScope;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.OnItemClick;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.RecyclerViewMovieAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
@FragmentScope
public class NowPlayingFragment extends Fragment implements NowPlayingView  ,OnItemClick<Movie> {
    public static final String NOW_PLAYING_KEY = "nowplaying";
    ArrayList<Movie> movies = new ArrayList<>();
    @BindView(R.id.recycler_view_now_playing)RecyclerView recyclerView;
    @Inject
    NowPlayingPresenter presenter;
    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void injectFragment() {
        Context context=getActivity().getApplicationContext();
        FragmentComponent injector =((BaseApp)context).getAppComponent().fragmentComponent().build();
        injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);
        bindModule(view);
        //setup view

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.performLoadNowPlayingList();
        return view;
    }

    private void bindModule(View view) {
        ButterKnife.bind(this,view);
        this.injectFragment();
        presenter.bind(this);
    }


    @Override
    public void showNowPlayingList(List<Movie> movieList) {

        movies.addAll(movieList);
        RecyclerViewMovieAdapter adapter = new RecyclerViewMovieAdapter(movieList ,NOW_PLAYING_KEY ,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Movie item) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_MOVIE_KEY,item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Movie.class.getSimpleName(),movies);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ( savedInstanceState!= null){
            movies = savedInstanceState.getParcelableArrayList(Movie.class.getSimpleName());
            RecyclerViewMovieAdapter adapter = new RecyclerViewMovieAdapter(movies,NOW_PLAYING_KEY,this);
            recyclerView.setAdapter(adapter);
        }


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }
}
