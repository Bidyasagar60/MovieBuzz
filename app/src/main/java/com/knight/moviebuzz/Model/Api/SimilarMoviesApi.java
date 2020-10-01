package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SimilarMoviesApi {
    @GET("/3/movie/{movie_id}/similar")
    Flowable<SimilarMovieResult> SimilarMovieList(

            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key);
}
