package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.PopularMovieNetwrokCallData;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;

public class PopularMovieRepo {


    private static PopularMovieRepo popularMovieRepo;
    private static PopularMovieNetwrokCallData popularMovieNetwrokCallData;
    private static MovieBuzzClient movieBuzzClient;

    private LiveData<MovieListResult> Movies;

    private PopularMovieRepo(){}

    public static PopularMovieRepo getINSTANCE()
    {
        movieBuzzClient= MovieBuzzClient.getInstance();
        popularMovieNetwrokCallData=new PopularMovieNetwrokCallData(movieBuzzClient.getPopularMovieClient());
        if(popularMovieRepo== null)
        {
            popularMovieRepo=new PopularMovieRepo();
        }
        return popularMovieRepo;
    }

    public LiveData<MovieListResult>    getMovies() {

        popularMovieNetwrokCallData.fetchMovies();
        Movies=popularMovieNetwrokCallData.ObservePopularMovies();
        return Movies;

    }


}
