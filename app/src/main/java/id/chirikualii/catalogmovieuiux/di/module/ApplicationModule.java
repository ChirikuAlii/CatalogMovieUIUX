package id.chirikualii.catalogmovieuiux.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.chirikualii.catalogmovieuiux.data.ApiService;
import id.chirikualii.catalogmovieuiux.di.ApplicationContext;
import id.chirikualii.catalogmovieuiux.di.DatabaseInfo;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
       this.application = application;
    }

    @Provides
    //@ApplicationContext
    @Singleton
    Context providesContext(){
        return application;
    }

    @Provides
    @Singleton
    Retrofit provideRetorift(){

        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "movie.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 1;
    }
}