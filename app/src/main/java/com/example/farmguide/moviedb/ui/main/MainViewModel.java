package com.example.farmguide.moviedb.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.farmguide.moviedb.usecases.GetMoviesUseCase;
import com.example.farmguide.moviedb.data.db.Movie;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.functions.Consumer;

public class MainViewModel extends ViewModel {

    private GetMoviesUseCase getMoviesUseCase;
    private MutableLiveData<List<Movie>> data = new MutableLiveData<>();

    CompositeDisposable disposable = new CompositeDisposable();

    public MainViewModel(GetMoviesUseCase getMoviesUseCase){
        this.getMoviesUseCase = getMoviesUseCase;
    }

    public LiveData<List<Movie>> getMoviesObservable(){
        return data;
    }


    public void loadMovies(){
        disposable.add(getMoviesUseCase.getMovies().subscribe(
                new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        data.postValue(movies);
                    }
                }
        ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
