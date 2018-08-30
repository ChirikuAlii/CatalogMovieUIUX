package id.chirikualii.catalogmovieuiux.presentasion.ui.upcoming;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.chirikualii.catalogmovieuiux.BaseApp;
import id.chirikualii.catalogmovieuiux.R;
import id.chirikualii.catalogmovieuiux.di.component.FragmentComponent;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.RecyclerViewMovieAdapter;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.adapter.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements UpcomingView ,OnItemClick<Movie>{
    public static final String UPCOMING_KEY = "upcoming";
    @BindView(R.id.recycler_view_upcoming)RecyclerView recyclerView;
    @Inject
    UpcomingPresenter presenter;
    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        this.bindModule(view);

        //setup view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        presenter.performLoadUpcomingList();
        return view;
    }

    private void bindModule(View view) {
        ButterKnife.bind(this,view);
        this.injectFragement();
        presenter.bind(this);
    }

    private void injectFragement() {
        FragmentComponent injector = BaseApp.get(getActivity().getApplicationContext()).getAppComponent().fragmentComponent().build();
        injector.inject(this);
    }

    @Override
    public void showUpcomingList(List<Movie> upcomingList) {
        RecyclerViewMovieAdapter adapter = new RecyclerViewMovieAdapter(upcomingList ,UPCOMING_KEY,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onErrorLoad(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Movie item) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.DETAIL_MOVIE_KEY,item);
        startActivity(intent);
    }
}
