package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;
import com.knight.moviebuzz.Model.Repository.MovieCastAndCrewRepo;


public class MovieCastAndCrewViewModel extends ViewModel {





    private LiveData<MovieCastAndCrew> castAndCrewList;
    private MovieCastAndCrewRepo castAndCrewRepo;

    public void init(int movieId)
    {
        if(castAndCrewList!=null)
        {
            return;
        }
        castAndCrewRepo=MovieCastAndCrewRepo.getInstance();
        castAndCrewList=castAndCrewRepo.getAllCastAndCrew(movieId);
    }

    public LiveData<MovieCastAndCrew> getCastAndCrewList()

    {
        return castAndCrewList;
    }



}
