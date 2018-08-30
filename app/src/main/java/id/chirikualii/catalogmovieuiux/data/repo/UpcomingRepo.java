package id.chirikualii.catalogmovieuiux.data.repo;

import java.util.List;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.BuildConfig;
import id.chirikualii.catalogmovieuiux.Language;
import id.chirikualii.catalogmovieuiux.data.ApiService;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import io.reactivex.Flowable;

public class UpcomingRepo {

    ApiService service;

    @Inject
    public UpcomingRepo(ApiService service) {
        this.service = service;
    }

    public Flowable<List<Movie>> loadUpcomingList(){
        return service.getUpcomingMovie(BuildConfig.MOVIE_API_KEY)
                .flatMap(it -> Flowable.fromIterable(it.getUpcomingModels()))
                .map(its -> new Movie(
                        its.getOriginalTitle(),
                        its.getOverview(),
                        its.getReleaseDate(),
                        its.getPosterPath(),
                        its.getBackdropPath(),
                        its.getVoteAverage()))
                .toList()
                .toFlowable();
    }
}
