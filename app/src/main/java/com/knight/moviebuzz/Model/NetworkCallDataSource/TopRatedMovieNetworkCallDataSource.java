package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


import com.knight.moviebuzz.Model.Api.TopRatedMovieApi;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopRatedMovieNetworkCallDataSource {


    private MediatorLiveData<MovieListResult> TopRatedMovies=new MediatorLiveData<>();
    private TopRatedMovieApi topRatedMovieApi ;




    public TopRatedMovieNetworkCallDataSource( TopRatedMovieApi topRatedMovieApi) {

        this.topRatedMovieApi = topRatedMovieApi;
    }


    public void fetchMovies()
    {


         Constants.KEY_STATUS=Constants.KEY_LOADING;

        final LiveData<MovieListResult> source= LiveDataReactiveStreams.fromPublisher(
                topRatedMovieApi.TopRatedMovieList(Constants.API_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()));

        TopRatedMovies.addSource(source, new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {
                TopRatedMovies.setValue(movieListResult);
                TopRatedMovies.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });



    }






    public LiveData<MovieListResult> ObserveTopRatedMovies()
    {
        return TopRatedMovies;

    }



}
