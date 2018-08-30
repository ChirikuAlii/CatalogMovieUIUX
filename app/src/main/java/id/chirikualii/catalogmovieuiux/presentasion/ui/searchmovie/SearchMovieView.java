package id.chirikualii.catalogmovieuiux.presentasion.ui.searchmovie;

import java.util.List;

import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public interface SearchMovieView {

    void showMovieList(List<Movie> movieList);
    void setOnErrorLoadMovie(String message);
}
