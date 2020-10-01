package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;
import com.knight.moviebuzz.Model.Pojo.MoviesDetail;
import com.knight.moviebuzz.Model.Repository.MovieDetailsRepo;

public class MovieDetailsViewModel extends ViewModel {


    private LiveData<MoviesDetail> MovieDeatils;
    private MovieDetailsRepo movieDetailsRepo;


    public void init(int movieId)
    {
      if(movieDetailsRepo!=null)
      {
          return;
      }

      movieDetailsRepo=MovieDetailsRepo.getInstance();
      MovieDeatils=movieDetailsRepo.getMovieDeatils(movieId);
    }

    public LiveData<MoviesDetail> getMovieDeatils() {
        return MovieDeatils;
    }
}
