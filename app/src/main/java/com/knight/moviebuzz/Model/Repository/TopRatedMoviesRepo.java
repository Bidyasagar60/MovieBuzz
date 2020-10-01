package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.TopRatedMovieNetworkCallDataSource;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;

public class TopRatedMoviesRepo {
    private static TopRatedMoviesRepo topRatedMoviesRepo;
    private static TopRatedMovieNetworkCallDataSource topRatedMovieNetworkCallDataSource;
    private static MovieBuzzClient movieBuzzClient;

    private LiveData<MovieListResult> Movies;

    private TopRatedMoviesRepo(){}

    public static TopRatedMoviesRepo getINSTANCE()
    {
        movieBuzzClient= MovieBuzzClient.getInstance();
        topRatedMovieNetworkCallDataSource=new TopRatedMovieNetworkCallDataSource(movieBuzzClient.getTopRatedMovieClient());
        if(topRatedMoviesRepo== null)
        {
            topRatedMoviesRepo=new TopRatedMoviesRepo();
        }
        return topRatedMoviesRepo;
    }

    public LiveData<MovieListResult>    getMovies() {

        topRatedMovieNetworkCallDataSource.fetchMovies();
        Movies=topRatedMovieNetworkCallDataSource.ObserveTopRatedMovies();
        return Movies;

    }

}
