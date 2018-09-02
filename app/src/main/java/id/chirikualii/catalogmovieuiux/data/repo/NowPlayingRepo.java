package id.chirikualii.catalogmovieuiux.data.repo;

import java.util.List;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.BuildConfig;
import id.chirikualii.catalogmovieuiux.Language;
import id.chirikualii.catalogmovieuiux.data.ApiService;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import io.reactivex.Flowable;

public class NowPlayingRepo {

    ApiService service;

    @Inject
    public NowPlayingRepo(ApiService service) {
        this.service = service;
    }

    public Flowable<List<Movie>> loadNowPlayingList(){
        return service.getNowPlayingMovie(BuildConfig.MOVIE_API_KEY)
                .flatMap(it -> Flowable.fromIterable(it.getResults()))
                .map(its -> new Movie(
                        its.getOriginalTitle(),
                        its.getOverview(),
                        its.getReleaseDate(),
                        its.getPosterPath(),
                        its.getBackdropPath(),
                        its.getVoteAverage().toString(),
                        its.getId().toString()
                ))
                .toList()
                .toFlowable();

    }
}
