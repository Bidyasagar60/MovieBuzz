package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.SimilarMoviesApi;
import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SimilarMovieNetworkCallData {

    private SimilarMoviesApi similarMoviesApi;
    private MediatorLiveData<SimilarMovieResult> SimilarMovieList;

    public SimilarMovieNetworkCallData(SimilarMoviesApi similarMoviesApi) {
        this.similarMoviesApi = similarMoviesApi;
        SimilarMovieList=new MediatorLiveData<>();
    }

    public void fetchSimilarMovies(int movieId)
    {
          Constants.KEY_STATUS=Constants.KEY_LOADING;
        final LiveData<SimilarMovieResult> source= LiveDataReactiveStreams.fromPublisher(similarMoviesApi.SimilarMovieList(movieId, Constants.API_KEY)
                                              .subscribeOn(Schedulers.io())
                                              .observeOn(AndroidSchedulers.mainThread()));

        SimilarMovieList.addSource(source, new Observer<SimilarMovieResult>() {
            @Override
            public void onChanged(SimilarMovieResult similarMovieResult) {
                SimilarMovieList.postValue(similarMovieResult);
                SimilarMovieList.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });

    }


    public LiveData<SimilarMovieResult> ObserveSimilarMovies()
    {
        return SimilarMovieList;
    }


}
