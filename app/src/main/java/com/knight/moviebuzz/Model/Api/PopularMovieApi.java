package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.MovieListResult;


import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularMovieApi {


     @GET("/3/movie/popular")
     Flowable<MovieListResult> PolularMovieList(

        @Query("api_key") String api_key);

}
