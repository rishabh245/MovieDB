package com.example.farmguide.moviedb.di;

import android.app.Application;
import android.content.Context;

import android.util.Log;

import com.example.farmguide.moviedb.data.api.ApiHelper;
import com.example.farmguide.moviedb.data.db.AppDatabase;
import com.example.farmguide.moviedb.data.api.ApiService;
import com.example.farmguide.moviedb.data.db.MovieDao;
import com.example.farmguide.moviedb.data.db.ReviewDao;
import com.example.farmguide.moviedb.repo.MoviesRepository;
import com.example.farmguide.moviedb.repo.MoviesRepositoryImpl;
import com.example.farmguide.moviedb.repo.ReviewRepository;
import com.example.farmguide.moviedb.repo.ReviewRepositoryImpl;
import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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



    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context){
        return  AppDatabase.getDatabase(context);
    }

    @Singleton
    @Provides
    MovieDao provideMovieDao (AppDatabase appDatabase){
        return appDatabase.movieDao();
    }

    @Singleton
    @Provides
    ReviewDao provideReviewDao(AppDatabase appDatabase){
        return appDatabase.reviewDao();
    }

    @Singleton
    @Provides
    MoviesRepository moviesRepository(MovieDao movieDao){
        return new MoviesRepositoryImpl(movieDao);
    }

    @Singleton
    @Provides
    ReviewRepository providesReviewRepository(ReviewDao reviewDao){
        return new ReviewRepositoryImpl(reviewDao);
    }




}
