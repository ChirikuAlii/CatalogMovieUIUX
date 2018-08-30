package id.chirikualii.catalogmovieuiux.di.component;

import dagger.Subcomponent;
import id.chirikualii.catalogmovieuiux.di.module.FragmentModule;
import id.chirikualii.catalogmovieuiux.di.scope.FragmentScope;
import id.chirikualii.catalogmovieuiux.presentasion.ui.nowplaying.NowPlayingFragment;
import id.chirikualii.catalogmovieuiux.presentasion.ui.upcoming.UpcomingFragment;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    @Subcomponent.Builder
    interface  Builder{
        FragmentComponent.Builder fragmentyModule(FragmentModule fragmentModule);

        FragmentComponent build();
    }
    void inject(NowPlayingFragment nowPlayingFragment);
    void inject(UpcomingFragment upcomingFragment);
}
