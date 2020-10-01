package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.MovieLanguageApi;

import com.knight.moviebuzz.Model.Pojo.MovieLanguages;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieLanguageNetworkCallData {


    private MediatorLiveData<MovieLanguages> MovieLanguages=new MediatorLiveData<>();
    private MovieLanguageApi movieLanguageApi;

    public MovieLanguageNetworkCallData(MovieLanguageApi movieLanguageApi) {
        this.movieLanguageApi = movieLanguageApi;
    }

    public void fetchMovieLanguagaes(int MovieId)
    {

        Constants.KEY_STATUS=Constants.KEY_LOADING;

        final LiveData<MovieLanguages> source= LiveDataReactiveStreams.fromPublisher(
                movieLanguageApi.MOVIE_LANGUAGES_CALL(MovieId,Constants.API_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()));

        MovieLanguages.addSource(source, new Observer<com.knight.moviebuzz.Model.Pojo.MovieLanguages>() {
            @Override
            public void onChanged(com.knight.moviebuzz.Model.Pojo.MovieLanguages movieLanguages) {
                MovieLanguages.setValue(movieLanguages);
                MovieLanguages.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;

            }
        });



    }






    public LiveData<MovieLanguages> ObserveMovieLanguages()
    {
        return MovieLanguages;

    }



}
