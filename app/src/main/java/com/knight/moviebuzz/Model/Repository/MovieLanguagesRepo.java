package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;


import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.MovieLanguageNetworkCallData;
import com.knight.moviebuzz.Model.Pojo.MovieLanguages;


public class MovieLanguagesRepo {


    private static MovieLanguagesRepo movieLanguagesRepo;
    private static MovieLanguageNetworkCallData movieLanguageNetworkCallData;
    private static MovieBuzzClient movieLanguageClient;


    private LiveData<MovieLanguages> Languages;

    private MovieLanguagesRepo(){}

    public static MovieLanguagesRepo getINSTANCE()
    {
        movieLanguageClient= MovieBuzzClient.getInstance();
        movieLanguageNetworkCallData=new MovieLanguageNetworkCallData(movieLanguageClient.getMovieLanguageClient());
        if(movieLanguagesRepo== null)
        {
            movieLanguagesRepo=new MovieLanguagesRepo();
        }
        return movieLanguagesRepo;
    }

    public LiveData<MovieLanguages>    getLanguages(int MovieId) {

        movieLanguageNetworkCallData.fetchMovieLanguagaes(MovieId);
        Languages=movieLanguageNetworkCallData.ObserveMovieLanguages();
        return Languages;

    }
}
