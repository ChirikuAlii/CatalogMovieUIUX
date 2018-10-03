package id.chirikualii.catalogmovieuiux.data;

import id.chirikualii.catalogmovieuiux.data.model.NowPlayingResponse;
import id.chirikualii.catalogmovieuiux.data.model.SearchMovieResponse;
import id.chirikualii.catalogmovieuiux.data.model.UpcomingResponse;
import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing?language=en-US")
    Flowable<NowPlayingResponse> getNowPlayingMovie(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming?")
    Flowable<UpcomingResponse> getUpcomingMovie(
            @Query("api_key") String apiKey
    );

    @GET("search/movie?")
    Flowable<SearchMovieResponse> getSearchingMovie(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("movie/upcoming?")
    Call<UpcomingResponse> getUpcomingMovies(
            @Query("api_key") String apiKey
    );
}
