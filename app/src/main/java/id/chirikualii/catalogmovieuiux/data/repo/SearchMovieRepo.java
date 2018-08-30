package id.chirikualii.catalogmovieuiux.data.repo;

import java.util.List;

import javax.inject.Inject;

import id.chirikualii.catalogmovieuiux.BuildConfig;
import id.chirikualii.catalogmovieuiux.Language;
import id.chirikualii.catalogmovieuiux.data.ApiService;
import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;
import io.reactivex.Flowable;

public class SearchMovieRepo {

    ApiService service;

    @Inject
    public SearchMovieRepo(ApiService service) {
        this.service = service;
    }

    public Flowable<List<Movie>> loadSearchMovieList(String query){

        return service.getSearchingMovie(BuildConfig.MOVIE_API_KEY,query)
                .flatMap(it -> Flowable.fromIterable(it.getResults()))
                .map(
                        it->
                                new Movie(
                                        it.getOriginalTitle(),
                                        it.getOverview(),
                                        it.getReleaseDate(),
                                        it.getPosterPath(),
                                        it.getBackdropPath(),
                                        it.getVoteAverage()

                                )
                )
                .toList()
                .toFlowable();
    }
}
