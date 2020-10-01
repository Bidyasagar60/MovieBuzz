package com.knight.moviebuzz.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;
import com.knight.moviebuzz.Model.Repository.SimilarMoviesRepo;

public class SimilarMovieViewModel extends ViewModel {

    private SimilarMoviesRepo similarMoviesRepo;
    private LiveData<SimilarMovieResult> similarMovieList;



    public void  init(int movieId)
    {
        if(similarMovieList!=null)
        {
            return;
        }
        similarMoviesRepo=SimilarMoviesRepo.getInstance();
        similarMovieList=similarMoviesRepo.getSimilarMovieList(movieId);
    }

    public LiveData<SimilarMovieResult> getSimilarMovieList() {
        return similarMovieList;
    }
}
