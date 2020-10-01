package com.knight.moviebuzz.Model.Api;

import com.knight.moviebuzz.Model.Pojo.MovieLanguages;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieLanguageApi {
    @GET("/3/movie/{movie_id}/translations")
    Flowable<MovieLanguages> MOVIE_LANGUAGES_CALL(

            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key);
}
