package com.knight.moviebuzz.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.knight.moviebuzz.AdapterHouse.CastAdapter;
import com.knight.moviebuzz.AdapterHouse.SimilarMoviesAdapter;
import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;
import com.knight.moviebuzz.Model.Pojo.MovieLanguages;
import com.knight.moviebuzz.Model.Pojo.MovieTrailer;
import com.knight.moviebuzz.Model.Pojo.MoviesDetail;
import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;
import com.knight.moviebuzz.R;
import com.knight.moviebuzz.Utility.Constants;
import com.knight.moviebuzz.ViewModel.MovieCastAndCrewViewModel;
import com.knight.moviebuzz.ViewModel.MovieDetailsViewModel;
import com.knight.moviebuzz.ViewModel.MovieLanguageViewModel;
import com.knight.moviebuzz.ViewModel.MovieTrailerViewModel;
import com.knight.moviebuzz.ViewModel.SimilarMovieViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieDetailActivity extends AppCompatActivity {

    private static final String API_KEY="4e44d9029b1270a757cddc766a1bcb63";
    private static  final String BASE_URL="https://api.themoviedb.org";
    private int movie_id;
    private TextView MovieTitle,MovieReleaseDate,MovieGenerics,MovieOverview,MovieLanguage,MovieWriter,MovieDirector,MovieProducer;
     public static TextView Similar;
    private RatingBar MovieRating;
    private ImageView MoviePoster,BackDropPoster;
    private com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView TrailerPlayer;
    private RecyclerView SimilarMovieRcView,CastRcView;
    private SimilarMoviesAdapter similarMoviesAdapter;
    private CastAdapter castAdapter;
    private MovieDetailsViewModel movieDetailsViewModel;
    private MovieTrailerViewModel movieTrailerViewModel;
    private SimilarMovieViewModel similarMovieViewModel;
    private MovieCastAndCrewViewModel movieCastAndCrewViewModel;
    private MovieLanguageViewModel movieLanguageViewModel;

    private Handler handler=new Handler();
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);



        progressBar=findViewById(R.id.progressbarD);
        MoviePoster=findViewById(R.id.Movie_poster);
        MovieGenerics=findViewById(R.id.Movie_Generics);
        MovieLanguage=findViewById(R.id.MovieLanguage);
        MovieOverview=findViewById(R.id.Overview);
        MovieReleaseDate=findViewById(R.id.MovieReleaseDate);
        MovieTitle=findViewById(R.id.MovieTitle);
        MovieRating=findViewById(R.id.MovieRating);
        TrailerPlayer=findViewById(R.id.TrailerView);
        BackDropPoster=findViewById(R.id.Movie_poster_backdrop);
        SimilarMovieRcView=findViewById(R.id.SimilarMoviesRc);
        Similar=findViewById(R.id.SimilarMovieRcTitle);
        MovieWriter=findViewById(R.id.StoryWriter);
        MovieDirector=findViewById(R.id.Director);
        MovieProducer=findViewById(R.id.Producer);
        CastRcView=findViewById(R.id.CastRc);

        SimilarMovieRcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        CastRcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




        movie_id=getIntent().getIntExtra("Movieid",0);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            MovieOverview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        similarMovieViewModel= ViewModelProviders.of(this).get(SimilarMovieViewModel.class);
        movieDetailsViewModel=ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieCastAndCrewViewModel=ViewModelProviders.of(this).get(MovieCastAndCrewViewModel.class);
        movieTrailerViewModel=ViewModelProviders.of(this).get(MovieTrailerViewModel.class);
        movieLanguageViewModel=ViewModelProviders.of(this).get(MovieLanguageViewModel.class);

      ShowMovieDeatils(movie_id);
        ShowMovieTrailer(movie_id);
        ShowCastCrew(movie_id);
        ShowSimiliarMovies(movie_id);

    }

    private void ShowSimiliarMovies(int movie_id) {

        similarMovieViewModel.init(movie_id);
        similarMovieViewModel.getSimilarMovieList().observe(this, new Observer<SimilarMovieResult>() {
            @Override
            public void onChanged(SimilarMovieResult similarMovieResult) {
                List<SimilarMovieResult.ResultsBean> similarmovieList=similarMovieResult.getResults();

                if (similarmovieList.isEmpty())
                {
                    Similar.setVisibility(View.INVISIBLE);
                }
                similarMoviesAdapter=new SimilarMoviesAdapter(MovieDetailActivity.this,similarmovieList);
                SimilarMovieRcView.setAdapter(similarMoviesAdapter);

                similarMoviesAdapter.notifyDataSetChanged();
            }
        });



    }

    private void ShowCastCrew(int movie_id) {


   movieCastAndCrewViewModel.init(movie_id);
   movieCastAndCrewViewModel.getCastAndCrewList().observe(this, new Observer<MovieCastAndCrew>() {
       @Override
       public void onChanged(MovieCastAndCrew movieCastAndCrew) {


           List<MovieCastAndCrew.CrewBean> AllCrew=movieCastAndCrew.getCrew();
           List<MovieCastAndCrew.CastBean> AllCast=movieCastAndCrew.getCast();
           String Writer="";
           String Producer="";
           String Director="";

           for (int position=0;position<AllCrew.size();position++)
           {
               MovieCastAndCrew.CrewBean crew=AllCrew.get(position);
               if(Writer.isEmpty())
               {
                   if(crew.getJob().equals("Writer"))
                       Writer=crew.getName();
               }
               else {
                   if(crew.getJob().equals("Writer"))
                       Writer=Writer+","+crew.getName();

               }

           }

           for (int position=0;position<AllCrew.size();position++)
           {
               MovieCastAndCrew.CrewBean crew=AllCrew.get(position);

               if(crew.getJob().equals("Producer"))
               {

               }
               if(Producer.isEmpty())
               {

                   Producer=crew.getName();
               }
               else {
                   if(crew.getJob().equals("Producer"))
                       Producer=Producer+","+crew.getName();

               }

           }

           for (int position=0;position<AllCrew.size();position++)
           {
               MovieCastAndCrew.CrewBean crew=AllCrew.get(position);
               if(Director.isEmpty())
               {
                   if(crew.getJob().equals("Director"))
                       Director=crew.getName();
               }
               else {
                   if(crew.getJob().equals("Director"))
                       Director=Director+","+crew.getName();

               }

           }

           if(Writer.isEmpty())
               MovieWriter.setText(": N/A");
           else
               MovieWriter.setText(": "+Writer);

           if(Director.isEmpty())
               MovieDirector.setText(": N/A");
           else
               MovieDirector.setText(": "+Director);

           if(Producer.isEmpty())
               MovieProducer.setText(": N/A");
           else
               MovieProducer.setText(": "+Producer);

           castAdapter=new CastAdapter(MovieDetailActivity.this,AllCast);
           CastRcView.setAdapter(castAdapter);
           castAdapter.notifyDataSetChanged();









       }
   });



    }

    private void ShowMovieTrailer(int movie_id) {


        movieTrailerViewModel.init(movie_id);
        movieTrailerViewModel.getMovieTrailers().observe(this, new Observer<MovieTrailer>() {
            @Override
            public void onChanged(MovieTrailer movieTrailer) {

                List<MovieTrailer.ResultsBean> movietrailers=movieTrailer.getResults();



                for( MovieTrailer.ResultsBean trailer:movietrailers)
                {
                    if(trailer.getType().equals("Trailer"))
                    {
                        final String url=trailer.getKey();
                        Log.d("TAG", "video Url: "+url);
                        TrailerPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(YouTubePlayer youTubePlayer) {
                                youTubePlayer.cueVideo(url,0f);
                            }
                        });
                        break;
                    }

                }

            }
        });


    }

    private void ShowMovieDeatils(int movie_id) {

     movieDetailsViewModel.init(movie_id);
     movieDetailsViewModel.getMovieDeatils().observe(this, new Observer<MoviesDetail>() {
         @Override
         public void onChanged(MoviesDetail moviesDetail) {
             MovieTitle.setText(moviesDetail.getTitle().toUpperCase());
             MovieReleaseDate.setText(": "+moviesDetail.getRelease_date());
             String Language_Code=moviesDetail.getOriginal_language();
             ShowLanguage(Language_Code);
             MovieRating.setRating((float) moviesDetail.getVote_average()/2);
             MovieOverview.setText(moviesDetail.getOverview());
             List<MoviesDetail.GenresBean> genresBean=moviesDetail.getGenres();
             String genres="";
             for(MoviesDetail.GenresBean bean:genresBean)
             {

                 if(genres.isEmpty()) {

                     genres = bean.getName();
                 }
                 else {
                     genres=genres+","+bean.getName();
                 }

             }


             MovieGenerics.setText(": "+genres);
             String image_url="https://image.tmdb.org/t/p/w500/"+moviesDetail.getPoster_path();

             Picasso.get().load(image_url).into(MoviePoster);
             String _backdrop_image_url="https://image.tmdb.org/t/p/w500/"+moviesDetail.getBackdrop_path();
             Picasso.get().load(_backdrop_image_url).into(BackDropPoster);




         }
     });



    }

    private void ShowLanguage(final String language_code) {

        movieLanguageViewModel.init(movie_id);
        movieLanguageViewModel.getMovieLanguages().observe(this, new Observer<MovieLanguages>() {
            @Override
            public void onChanged(MovieLanguages movieLanguages) {


                List<MovieLanguages.TranslationsBean> translationsBeans=movieLanguages.getTranslations();
                String Language="";
                for(MovieLanguages.TranslationsBean movieLanguage:translationsBeans)
                {

                    if(movieLanguage.getIso_639_1().equals(language_code))
                    {

                        MovieLanguage.setText(": "+movieLanguage.getEnglish_name());

                        Language=movieLanguage.getEnglish_name();
                        break;
                    }


                }
                if (Language.isEmpty())
                {

                    MovieLanguage.setText(": N/A");

                }




            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public void onPause() {
        handler.removeCallbacks(progresbarthread);
        super.onPause();

    }

    @Override
    public void onResume() {

        super.onResume();
        handler.postDelayed(progresbarthread,0);
    }

    private Runnable progresbarthread=new Runnable() {
        @Override
        public void run() {
            Log.d("ZTAG", "run: "+ Constants.KEY_STATUS);

            if(Constants.KEY_STATUS==Constants.KEY_LOADING)
            {
                progressBar.setVisibility(View.VISIBLE);
            }
            else if(Constants.KEY_STATUS==Constants.KEY_LOADING_COMPLETE)
            {
                progressBar.setVisibility(View.INVISIBLE);

            }
            handler.postDelayed(this,1000);

        }
    };

}