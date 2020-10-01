package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.MovieTrailerNetworkCallData;
import com.knight.moviebuzz.Model.Pojo.MovieTrailer;

public class MovieTrailerRepo {


    private  static MovieTrailerRepo movieTrailerRepo;
    private  LiveData<MovieTrailer> movieTrailers;
    private static MovieBuzzClient movieBuzzClient;
    private static MovieTrailerNetworkCallData movieTrailerNetworkCallData;


    private MovieTrailerRepo() {
    }

    public static MovieTrailerRepo  getInstance() {

        movieBuzzClient=MovieBuzzClient.getInstance();
        movieTrailerNetworkCallData=new MovieTrailerNetworkCallData(movieBuzzClient.getMovieTrailerClient());

        if (movieTrailerRepo == null) {
            movieTrailerRepo = new MovieTrailerRepo();
        }
        return movieTrailerRepo;
    }

    public LiveData<MovieTrailer> getMovieTrailers(int movieId) {
        movieTrailerNetworkCallData.fetchMovieTrailer(movieId);
        movieTrailers=movieTrailerNetworkCallData.ObserveMovieTrailer();

        return movieTrailers;
    }
}
