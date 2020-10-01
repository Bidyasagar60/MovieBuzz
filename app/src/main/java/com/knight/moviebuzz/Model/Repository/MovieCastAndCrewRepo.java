package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.CastAndCrewNetworkCallData;
import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;

public class MovieCastAndCrewRepo {

    private static MovieCastAndCrewRepo movieCastAndCrewRepo;
    private static CastAndCrewNetworkCallData castAndCrewNetworkCallData;
    private static MovieBuzzClient movieBuzzClient;
    private LiveData<MovieCastAndCrew> AllCastAndCrew;

    private MovieCastAndCrewRepo() {
    }

    public static MovieCastAndCrewRepo getInstance()
    {
        movieBuzzClient=MovieBuzzClient.getInstance();
        castAndCrewNetworkCallData=new CastAndCrewNetworkCallData(movieBuzzClient.getCastAndCrewClient());
        if(movieCastAndCrewRepo==null)
        {
            movieCastAndCrewRepo=new MovieCastAndCrewRepo();
        }
        return movieCastAndCrewRepo;
    }

    public LiveData<MovieCastAndCrew> getAllCastAndCrew(int movieId) {
        castAndCrewNetworkCallData.fetchMovieCastAndCrew(movieId);
        AllCastAndCrew=castAndCrewNetworkCallData.ObserveCastAndCrew();
        return AllCastAndCrew;
    }
}
