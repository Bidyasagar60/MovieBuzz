package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.Model.Repository.UpcomingMoviesRepo;

public class UpcomingMoviesViewModel extends ViewModel {


    private LiveData<MovieListResult> UpcomingMovies;
    private UpcomingMoviesRepo upcomingMoviesRepo;

    public void init()
    {
        if(UpcomingMovies!=null)
        {
            return;
        }
        upcomingMoviesRepo=UpcomingMoviesRepo.getINSTANCE();
        UpcomingMovies=upcomingMoviesRepo.getMovies();
    }

    public LiveData<MovieListResult> getUpcomingMovies()

    {
        return UpcomingMovies;
    }


}
