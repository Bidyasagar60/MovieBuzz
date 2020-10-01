package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.MovieDetailsNetworkCallData;
import com.knight.moviebuzz.Model.Pojo.MoviesDetail;

public class MovieDetailsRepo {


    private static MovieDetailsRepo movieDetailsRepo;
    private static MovieDetailsNetworkCallData movieDetailsNetworkCallData;
    private static MovieBuzzClient movieBuzzClient;
    private LiveData<MoviesDetail> MovieDeatils;

    private MovieDetailsRepo() {
    }

    public static MovieDetailsRepo getInstance()
    {
        movieBuzzClient=MovieBuzzClient.getInstance();
        movieDetailsNetworkCallData=new MovieDetailsNetworkCallData(movieBuzzClient.getMovieDetailsClient());
        if(movieDetailsRepo==null)
        {
            movieDetailsRepo=new MovieDetailsRepo();
        }
        return movieDetailsRepo;
    }


    public LiveData<MoviesDetail> getMovieDeatils(int movieId) {

        movieDetailsNetworkCallData.fetchMovieDetails(movieId);
        MovieDeatils=movieDetailsNetworkCallData.ObserveMovieDetails();
        return MovieDeatils;
    }
}
