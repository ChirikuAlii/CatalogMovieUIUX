package id.chirikualii.catalogmovieuiux.presentasion.ui.searchmovie;


import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.data.repo.SearchMovieRepo;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchMoviePresenter implements ISearchMoviePresenter  {
    private final static String TAG = SearchMoviePresenter.class.getSimpleName();
    private SearchMovieView view;
    private SearchMovieRepo repo;

    @Inject
    public SearchMoviePresenter(SearchMovieRepo repo) {
        this.repo = repo;
    }

    public void bind (SearchMovieView view){
        this.view = view;
    }

    @Override
    public void performLoadMovie(String query) {
        repo.loadSearchMovieList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it-> {
                            Log.d(TAG,"Search List: " + new Gson().toJsonTree(it));
                            view.showMovieList(it);
                        },
                        it -> {
                            Log.d(TAG,"Error:" +it.getMessage() );
                            view.setOnErrorLoadMovie(it.getMessage());
                        }
                )
                .isDisposed();
    }
}

