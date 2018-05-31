package com.example.farmguide.moviedb.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.util.Log;

import com.example.farmguide.moviedb.data.model.db.AppDatabase;
import com.example.farmguide.moviedb.data.model.api.ApiHelper;
import com.example.farmguide.moviedb.data.model.api.ApiService;
import com.example.farmguide.moviedb.data.model.db.Migration_1_2;
import com.example.farmguide.moviedb.usecases.GetMoviesUseCase;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    @Provides
    Context provideApplicationContext(Application application){
        return application.getApplicationContext();
    }





    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        Log.d("tagg" , "apiService");
        return retrofit.create(ApiService.class);
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Log.d("tagg" , "Retrofit");
        return  new Retrofit.Builder().
                baseUrl("https://api.themoviedb.org/").
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context , Migration[] migrations){
        return Room.databaseBuilder(context, AppDatabase.class, "database-name")
                .addMigrations(migrations).build();
    }

    @Singleton
    @Provides
    Migration[] providesMigrations(){
        Migration[] migrations = {new Migration_1_2()};
        return  migrations;
    }



}
