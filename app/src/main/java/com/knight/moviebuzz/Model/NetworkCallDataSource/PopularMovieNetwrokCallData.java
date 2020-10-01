package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.PopularMovieApi;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PopularMovieNetwrokCallData {


    private MediatorLiveData<MovieListResult> PolularMovies=new MediatorLiveData<>();
    private PopularMovieApi popularMovieApi;




    public PopularMovieNetwrokCallData( PopularMovieApi popularMovieApi) {

        this.popularMovieApi = popularMovieApi;
    }


    public void fetchMovies()
    {

     Constants.KEY_STATUS=Constants.KEY_LOADING;
       final LiveData<MovieListResult> source= LiveDataReactiveStreams.fromPublisher(
                                           popularMovieApi.PolularMovieList(Constants.API_KEY)
                                           .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread()));

       PolularMovies.addSource(source, new Observer<MovieListResult>() {
           @Override
           public void onChanged(MovieListResult movieListResult) {
               PolularMovies.setValue(movieListResult);
               PolularMovies.removeSource(source);
               Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
           }
       });



    }






    public LiveData<MovieListResult> ObservePopularMovies()
    {
      return PolularMovies;

    }




}
