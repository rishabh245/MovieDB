package com.example.farmguide.moviedb.ui.review;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.farmguide.moviedb.data.api.ApiHelper;
import com.example.farmguide.moviedb.data.db.Review;
import com.example.farmguide.moviedb.repo.ReviewRepository;
import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ReviewViewModel extends ViewModel {

    private MutableLiveData<List<Review>> data = new MutableLiveData<>();
    private GetReviewsUseCase getReviewsUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ReviewViewModel(GetReviewsUseCase getReviewsUseCase) {
        this.getReviewsUseCase = getReviewsUseCase;
    }

    public LiveData<List<Review>> getReviewObservable(){
        return data;
    }

    public void loadData(int moviesId){
        compositeDisposable.add(getReviewsUseCase.getReviews(moviesId)
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviews) throws Exception {
                        data.postValue(reviews);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
