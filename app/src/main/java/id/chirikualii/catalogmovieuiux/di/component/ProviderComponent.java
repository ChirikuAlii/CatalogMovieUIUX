package id.chirikualii.catalogmovieuiux.di.component;

import dagger.Component;
import dagger.Subcomponent;
import id.chirikualii.catalogmovieuiux.data.provider.FavoriteMovieProvider;
import id.chirikualii.catalogmovieuiux.di.module.ProviderModule;
import id.chirikualii.catalogmovieuiux.di.scope.ProviderScope;

@ProviderScope
@Subcomponent(modules = ProviderModule.class)
public interface ProviderComponent {
    @Subcomponent.Builder
    interface Builder{
        ProviderComponent.Builder providerModule(ProviderModule providerModule);

        ProviderComponent build();
    }
    void inject(FavoriteMovieProvider favoriteMovieProvider);
}

