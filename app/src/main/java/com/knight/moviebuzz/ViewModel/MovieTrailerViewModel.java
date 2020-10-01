package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieTrailer;
import com.knight.moviebuzz.Model.Repository.MovieTrailerRepo;

public class MovieTrailerViewModel extends ViewModel {

    private MovieTrailerRepo movieTrailerRepo;
    private LiveData<MovieTrailer> movieTrailers;

    public void init(int movieId)
    {
        if(movieTrailers!=null)
        {
            return;
        }
        movieTrailerRepo=MovieTrailerRepo.getInstance();
        movieTrailers=movieTrailerRepo.getMovieTrailers(movieId);
    }

    public LiveData<MovieTrailer> getMovieTrailers() {
        return movieTrailers;
    }
}
