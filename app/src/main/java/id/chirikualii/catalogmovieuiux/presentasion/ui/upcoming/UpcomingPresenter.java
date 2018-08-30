package id.chirikualii.catalogmovieuiux.presentasion.ui.upcoming;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.data.repo.UpcomingRepo;
import id.chirikualii.catalogmovieuiux.di.scope.FragmentScope;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class UpcomingPresenter implements IUpcomingPresenter{
    private UpcomingView view;
    private UpcomingRepo repo;
    private static String TAG = UpcomingPresenter.class.getSimpleName();
    @Inject
    public UpcomingPresenter(UpcomingRepo repo) {
        this.repo = repo;
    }

    public void bind (UpcomingView view){
        this.view = view;
    }

    @Override
    public void performLoadUpcomingList() {
        repo.loadUpcomingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it ->{
                            Log.d(TAG , "Upcominglist: " + new Gson().toJsonTree(it));
                            view.showUpcomingList(it);
                        },
                        it ->{
                            Log.e(TAG, "Error" + it.getMessage());
                            view.onErrorLoad(it.getMessage());

                        }
                )
                .isDisposed();
    }
}
