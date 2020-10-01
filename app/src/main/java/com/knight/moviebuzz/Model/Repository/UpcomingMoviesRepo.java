package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.UpcomingMovieNetworkDataCall;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;

public class UpcomingMoviesRepo {


    private static UpcomingMoviesRepo popularMovieRepo;
    private static UpcomingMovieNetworkDataCall upcomingMovieNetworkDataCall;
    private static MovieBuzzClient movieBuzzClient;

    private LiveData<MovieListResult> Movies;

    private UpcomingMoviesRepo(){}

    public static UpcomingMoviesRepo getINSTANCE()
    {
        movieBuzzClient= MovieBuzzClient.getInstance();
        upcomingMovieNetworkDataCall=new UpcomingMovieNetworkDataCall(movieBuzzClient.getUpcomingMovieClient());
        if(popularMovieRepo== null)
        {
            popularMovieRepo=new UpcomingMoviesRepo();
        }
        return popularMovieRepo;
    }

    public LiveData<MovieListResult>    getMovies() {

        upcomingMovieNetworkDataCall.fetchMovies();
        Movies=upcomingMovieNetworkDataCall.ObserveUpcomingMovies();
        return Movies;

    }
}
