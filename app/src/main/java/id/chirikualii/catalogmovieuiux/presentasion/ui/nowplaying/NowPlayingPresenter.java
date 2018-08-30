package id.chirikualii.catalogmovieuiux.presentasion.ui.nowplaying;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.data.repo.NowPlayingRepo;
import id.chirikualii.catalogmovieuiux.di.scope.FragmentScope;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class NowPlayingPresenter implements INowPlayingPresenter {
    private static String TAG = NowPlayingPresenter.class.getSimpleName();
    private NowPlayingRepo repo;
    private NowPlayingView view;

    @Inject
    public NowPlayingPresenter(NowPlayingRepo repo) {
        this.repo = repo;
    }

    public void bind(NowPlayingView view) {
        this.view = view;
    }


    @Override
    public void performLoadNowPlayingList() {
        repo.loadNowPlayingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        it -> {

                                Log.d(TAG, "Movie List :" + new Gson().toJsonTree(it));
                                view.showNowPlayingList(it);


                        }
                        ,
                        it -> Log.e(TAG, "Error" + it.getMessage())
                )
                .isDisposed();
    }
}
