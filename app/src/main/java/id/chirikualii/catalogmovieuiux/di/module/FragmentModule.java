package id.chirikualii.catalogmovieuiux.di.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import id.chirikualii.catalogmovieuiux.di.scope.FragmentScope;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    @FragmentScope
    Fragment provideFragment(){
        return fragment;
    }
}
