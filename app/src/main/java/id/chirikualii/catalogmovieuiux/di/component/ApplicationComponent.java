package id.chirikualii.catalogmovieuiux.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import id.chirikualii.catalogmovieuiux.data.db.DbHelper;
import id.chirikualii.catalogmovieuiux.di.module.ActivityModule;
import id.chirikualii.catalogmovieuiux.di.module.ApplicationModule;
import id.chirikualii.catalogmovieuiux.di.module.FragmentModule;
import id.chirikualii.catalogmovieuiux.di.module.ProviderModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityModule.class,
        FragmentModule.class,
        ProviderModule.class,
        AndroidInjectionModule.class
})
public interface ApplicationComponent {
    ActivityComponent.Builder activityComponent();
    FragmentComponent.Builder fragmentComponent();
    ProviderComponent.Builder providerComponent();
    void inject(Application app);

    DbHelper getHelper();
}