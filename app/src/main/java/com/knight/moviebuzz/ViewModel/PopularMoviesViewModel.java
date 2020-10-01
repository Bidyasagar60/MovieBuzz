package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Model.Repository.PopularMovieRepo;

public class PopularMoviesViewModel extends ViewModel {


    private LiveData<MovieListResult> PopularMovies;
    private PopularMovieRepo popularMovieRepo;

    public void init()
    {
        if(PopularMovies!=null)
        {
            return;
        }
        popularMovieRepo=PopularMovieRepo.getINSTANCE();
        PopularMovies=popularMovieRepo.getMovies();
    }

    public LiveData<MovieListResult> getPopularMovies()

    {
        return PopularMovies;
    }



}
