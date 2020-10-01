package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastAndCrewApi {

    @GET("/3/movie/{movie_id}/credits")
    Flowable<MovieCastAndCrew> CAST_AND_CREW_List(

            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key);
}
