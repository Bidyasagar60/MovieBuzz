package com.knight.moviebuzz.Model.Clients;

import com.knight.moviebuzz.Model.Api.CastAndCrewApi;
import com.knight.moviebuzz.Model.Api.MovieDetailApi;
import com.knight.moviebuzz.Model.Api.MovieLanguageApi;
import com.knight.moviebuzz.Model.Api.MovieTrailerApi;
import com.knight.moviebuzz.Model.Api.PopularMovieApi;
import com.knight.moviebuzz.Model.Api.SimilarMoviesApi;
import com.knight.moviebuzz.Model.Api.TopRatedMovieApi;
import com.knight.moviebuzz.Model.Api.UpcomingMovieApi;
import com.knight.moviebuzz.Utility.Constants;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieBuzzClient {

    private static MovieBuzzClient popularMovieClient;
    private static  final String POPULAR_MOVIE_BASE_URL="https://api.themoviedb.org";
    private static final String API_KEY="4e44d9029b1270a757cddc766a1bcb63";

   private MovieBuzzClient(){

   }
   public static MovieBuzzClient getInstance()
   {
       if(popularMovieClient==null)
       {
           popularMovieClient=new MovieBuzzClient();
       }
       return popularMovieClient;

   }


    public PopularMovieApi getPopularMovieClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        PopularMovieApi popularMovieApi=retrofit.create(PopularMovieApi.class);

        return popularMovieApi;

    }


    public MovieLanguageApi getMovieLanguageClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(Constants.POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieLanguageApi movieLanguageApi=retrofit.create(MovieLanguageApi.class);

        return movieLanguageApi;

    }


    public TopRatedMovieApi getTopRatedMovieClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        TopRatedMovieApi topRatedMovieApi=retrofit.create(TopRatedMovieApi.class);

        return topRatedMovieApi;

    }


    public UpcomingMovieApi getUpcomingMovieClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        UpcomingMovieApi upcomingMovieApi=retrofit.create(UpcomingMovieApi.class);

        return upcomingMovieApi;

    }


    public CastAndCrewApi getCastAndCrewClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        CastAndCrewApi castAndCrewApi=retrofit.create(CastAndCrewApi.class);

        return castAndCrewApi;

    }


    public MovieDetailApi getMovieDetailsClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDetailApi movieDetailApi=retrofit.create(MovieDetailApi.class);

        return movieDetailApi;

    }


    public MovieTrailerApi getMovieTrailerClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieTrailerApi movieTrailerApi=retrofit.create(MovieTrailerApi.class);

        return movieTrailerApi;

    }


    public SimilarMoviesApi getSimilarMovieClient()
    {
        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(POPULAR_MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        SimilarMoviesApi similarMoviesApi=retrofit.create(SimilarMoviesApi.class);

        return similarMoviesApi;

    }




}
