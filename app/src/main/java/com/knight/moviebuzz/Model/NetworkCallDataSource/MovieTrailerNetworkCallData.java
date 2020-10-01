package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.MovieTrailerApi;
import com.knight.moviebuzz.Model.Pojo.MovieTrailer;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieTrailerNetworkCallData {

    private MediatorLiveData<MovieTrailer> MovieTrailers;
    private MovieTrailerApi movieTrailerApi;

    public MovieTrailerNetworkCallData(MovieTrailerApi movieTrailerApi) {
        this.movieTrailerApi = movieTrailerApi;
        MovieTrailers=new MediatorLiveData<>();
    }


    public void fetchMovieTrailer(int movieId)
    {
        Constants.KEY_STATUS=Constants.KEY_LOADING;
        final LiveData<MovieTrailer> source= LiveDataReactiveStreams.fromPublisher(movieTrailerApi.MovieTrailerDetail(movieId, Constants.API_KEY)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()));

        MovieTrailers.addSource(source, new Observer<MovieTrailer>() {
            @Override
            public void onChanged(MovieTrailer movieTrailer) {
                MovieTrailers.postValue(movieTrailer);
                MovieTrailers.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });
    }


    public LiveData<MovieTrailer> ObserveMovieTrailer()
    {
        return MovieTrailers;
    }


}
