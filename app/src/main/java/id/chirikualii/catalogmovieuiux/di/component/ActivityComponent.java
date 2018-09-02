package id.chirikualii.catalogmovieuiux.di.component;

import dagger.Component;
import dagger.Subcomponent;

import id.chirikualii.catalogmovieuiux.di.module.ActivityModule;
import id.chirikualii.catalogmovieuiux.di.module.FragmentModule;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;
import id.chirikualii.catalogmovieuiux.presentasion.ui.detailmovie.DetailMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.favoritemovie.FavoriteMovieActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.main.MainActivity;
import id.chirikualii.catalogmovieuiux.presentasion.ui.searchmovie.SearchMovieActivity;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    @Subcomponent.Builder
    interface  Builder{
        ActivityComponent.Builder activityModule(ActivityModule activityModule);

        ActivityComponent build();
    }
    void inject(MainActivity mainActivity);
    void inject(SearchMovieActivity searchMovieActivity);
    void inject(DetailMovieActivity detailMovieActivity);
    void inject (FavoriteMovieActivity favoriteMovieActivity);

}
