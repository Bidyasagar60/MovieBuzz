package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieLanguages;
import com.knight.moviebuzz.Model.Repository.MovieLanguagesRepo;

public class MovieLanguageViewModel extends ViewModel {

    private LiveData<MovieLanguages> MovieLanguages;
    private MovieLanguagesRepo movieLanguagesRepo;

    public void init(int movieid) {

        movieLanguagesRepo = MovieLanguagesRepo.getINSTANCE();
        MovieLanguages = movieLanguagesRepo.getLanguages(movieid);
    }


    public LiveData<com.knight.moviebuzz.Model.Pojo.MovieLanguages> getMovieLanguages() {
        return MovieLanguages;
    }
}
