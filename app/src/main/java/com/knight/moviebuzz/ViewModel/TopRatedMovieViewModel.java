package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Model.Repository.TopRatedMoviesRepo;

public class TopRatedMovieViewModel extends ViewModel {

    private LiveData<MovieListResult> TopRatedMovies;
    private TopRatedMoviesRepo topRatedMoviesRepo;

    public void init()
    {
        if(TopRatedMovies!=null)
        {
            return;
        }
        topRatedMoviesRepo=TopRatedMoviesRepo.getINSTANCE();
        TopRatedMovies=topRatedMoviesRepo.getMovies();
    }

    public LiveData<MovieListResult> getPopularMovies()

    {
        return TopRatedMovies;
    }





}
