package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.UpcomingMovieApi;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpcomingMovieNetworkDataCall {


    private MediatorLiveData<MovieListResult> UpcomingMovies=new MediatorLiveData<>();
    private UpcomingMovieApi upcomingMovieApi;


    public UpcomingMovieNetworkDataCall(UpcomingMovieApi upcomingMovieApi) {
        this.upcomingMovieApi = upcomingMovieApi;
    }

    public void fetchMovies()
    {

          Constants.KEY_STATUS=Constants.KEY_LOADING;
        final LiveData<MovieListResult> source= LiveDataReactiveStreams.fromPublisher(
                upcomingMovieApi.UpComingMovieList(Constants.API_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()));

        UpcomingMovies.addSource(source, new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {
                UpcomingMovies.setValue(movieListResult);
                UpcomingMovies.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });



    }






    public LiveData<MovieListResult> ObserveUpcomingMovies()
    {
        return UpcomingMovies;

    }



}
