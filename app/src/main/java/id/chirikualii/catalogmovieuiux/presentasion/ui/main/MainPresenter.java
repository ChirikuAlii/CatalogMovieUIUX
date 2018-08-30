package id.chirikualii.catalogmovieuiux.presentasion.ui.main;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.data.repo.MainRepo;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class MainPresenter implements IMainPresenter{
    private static String TAG = MainPresenter.class.getSimpleName();
    private MainRepo repo;
    private MainView view;
    @Inject
    public MainPresenter(MainRepo repo) {
        this.repo = repo;
    }

    public void bind (MainView view){
        this.view = view;
    }

    @Override
    public void performSearchMovie(String query) {
        view.navigateToSearchActivity(query);
    }
}
