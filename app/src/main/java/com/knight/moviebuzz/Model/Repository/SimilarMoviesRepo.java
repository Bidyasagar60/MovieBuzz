package com.knight.moviebuzz.Model.Repository;

import androidx.lifecycle.LiveData;

import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.NetworkCallDataSource.SimilarMovieNetworkCallData;
import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;

public class SimilarMoviesRepo {


    private static SimilarMoviesRepo similarMoviesRepo;
    private LiveData<SimilarMovieResult> SimilarMovieList;
    private static SimilarMovieNetworkCallData similarMovieNetworkCallData;
    private static MovieBuzzClient movieBuzzClient;

    private SimilarMoviesRepo() {
    }

    public static SimilarMoviesRepo getInstance()
    {
        movieBuzzClient=MovieBuzzClient.getInstance();
        similarMovieNetworkCallData=new SimilarMovieNetworkCallData(movieBuzzClient.getSimilarMovieClient());
        if(similarMoviesRepo==null)
        {
            similarMoviesRepo=new SimilarMoviesRepo();
        }
        return similarMoviesRepo;
    }

    public LiveData<SimilarMovieResult> getSimilarMovieList(int movieId) {

        similarMovieNetworkCallData.fetchSimilarMovies(movieId);
        SimilarMovieList=similarMovieNetworkCallData.ObserveSimilarMovies();

        return SimilarMovieList;
    }
}
