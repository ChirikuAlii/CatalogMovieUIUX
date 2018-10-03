package id.chirikualii.catalogmovieuiux.di.module;

import android.content.ContentProvider;

import dagger.Module;
import dagger.Provides;
import id.chirikualii.catalogmovieuiux.di.scope.ProviderScope;

@Module
public class ProviderModule {

    private ContentProvider contentProvider;
    public ProviderModule(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @Provides
    @ProviderScope
    ContentProvider provideContentProvider() {
        return contentProvider;
    }
}
