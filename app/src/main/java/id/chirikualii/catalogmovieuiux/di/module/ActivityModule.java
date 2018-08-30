package id.chirikualii.catalogmovieuiux.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import id.chirikualii.catalogmovieuiux.di.ActivityContext;
import id.chirikualii.catalogmovieuiux.di.scope.ActivityScope;

@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;


    }

    @Provides
    @ActivityScope
    Activity provideActivity (){
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }


}

