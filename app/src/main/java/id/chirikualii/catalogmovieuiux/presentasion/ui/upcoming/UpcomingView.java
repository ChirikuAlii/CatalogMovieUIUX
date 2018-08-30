package id.chirikualii.catalogmovieuiux.presentasion.ui.upcoming;

import java.util.List;

import id.chirikualii.catalogmovieuiux.presentasion.model.Movie;

public interface UpcomingView {
    void showUpcomingList(List<Movie> upcomingList);
    void onErrorLoad(String message);

}
