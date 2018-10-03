package id.chirikualii.catalogmovieuiux;

import android.app.Application;
import android.content.Context;

import id.chirikualii.catalogmovieuiux.di.component.ApplicationComponent;
import id.chirikualii.catalogmovieuiux.di.component.DaggerApplicationComponent;
import id.chirikualii.catalogmovieuiux.di.module.ApplicationModule;

public class BaseApp extends Application {

    public static BaseApp get(Context context){
        return (BaseApp) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        getAppComponent().inject(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public ApplicationComponent getAppComponent(){
        return  DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
