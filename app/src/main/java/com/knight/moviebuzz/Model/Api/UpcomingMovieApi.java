package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.MovieListResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingMovieApi {

    @GET("/3/movie/upcoming")
    Flowable<MovieListResult> UpComingMovieList(

            @Query("api_key") String api_key);
}
