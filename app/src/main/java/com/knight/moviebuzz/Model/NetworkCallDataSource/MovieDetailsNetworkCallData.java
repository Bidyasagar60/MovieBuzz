package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.MovieDetailApi;
import com.knight.moviebuzz.Model.Pojo.MoviesDetail;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsNetworkCallData {

    private MediatorLiveData<MoviesDetail> moviesDetails;
    private MovieDetailApi movieDetailApi;

    public MovieDetailsNetworkCallData(MovieDetailApi movieDetailApi) {
        this.movieDetailApi = movieDetailApi;
        moviesDetails=new MediatorLiveData<>();
    }

    public void fetchMovieDetails(int movieId)
    {
        Constants.KEY_STATUS=Constants.KEY_LOADING;
        final LiveData<MoviesDetail> source= LiveDataReactiveStreams.fromPublisher(movieDetailApi.MovieDetail(movieId, Constants.API_KEY)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()));

        moviesDetails.addSource(source, new Observer<MoviesDetail>() {
            @Override
            public void onChanged(MoviesDetail moviesDetail) {
                moviesDetails.postValue(moviesDetail);
                moviesDetails.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });
    }


    public LiveData<MoviesDetail> ObserveMovieDetails()
    {
        return moviesDetails;
    }

}
