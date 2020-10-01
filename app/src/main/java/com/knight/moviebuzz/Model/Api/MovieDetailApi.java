package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.MoviesDetail;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailApi {


    @GET("/3/movie/{movie_id}")
    Flowable<MoviesDetail> MovieDetail(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key);

}
