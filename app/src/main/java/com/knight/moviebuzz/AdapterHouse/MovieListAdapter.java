package com.knight.moviebuzz.AdapterHouse;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.knight.moviebuzz.Model.Pojo.MovieLanguages;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.R;

import com.knight.moviebuzz.View.MovieDetailActivity;
import com.knight.moviebuzz.ViewModel.MovieLanguageViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<MovieListResult.ResultsBean> AllMovie;
    private static final String API_KEY="4e44d9029b1270a757cddc766a1bcb63";
    private static  final String BASE_URL="https://api.themoviedb.org";
    private MovieLanguageViewModel movieLanguageViewModel;


    public MovieListAdapter(Context context, List<MovieListResult.ResultsBean> allMovie) {
        this.context = context;
        AllMovie = allMovie;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_movie_rc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        MovieListResult.ResultsBean movie=AllMovie.get(position);
        holder.MovieTitle.setText(movie.getTitle());
        holder.MovieRating.setRating((float) movie.getVote_average()/2);
        final String Language_Code=movie.getOriginal_language();

        movieLanguageViewModel= ViewModelProviders.of((FragmentActivity) context).get(MovieLanguageViewModel.class);
        movieLanguageViewModel.init(movie.getId());
        Log.d("LANG", "MOVIE NAME: "+movie.getOriginal_title()+"LANG CODE: "+Language_Code);

        movieLanguageViewModel.getMovieLanguages().observe((LifecycleOwner) context, new Observer<MovieLanguages>() {
            @Override
            public void onChanged(MovieLanguages movieLanguages) {


                List<MovieLanguages.TranslationsBean> translationsBeans=movieLanguages.getTranslations();
                for(MovieLanguages.TranslationsBean movieLanguage:translationsBeans)
                {

                    if(movieLanguage.getIso_639_1().equals(Language_Code))
                    {
                        holder.MovieLanguage.setText("Language : "+movieLanguage.getEnglish_name());
                        Log.d("LANG", " LANG :"+movieLanguage.getEnglish_name());

                    }

              }



            }
        });





        holder.MovieReleaseDate.setText("Release Date : "+movie.getRelease_date());


        String image_url="https://image.tmdb.org/t/p/w500/"+movie.getPoster_path();

        Picasso.get().load(image_url).into(holder.MoviePoster);


    }

    @Override
    public int getItemCount() {
        return  AllMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView MovieTitle,MovieReleaseDate,MovieLanguage;
        private ImageView MoviePoster;
        private RelativeLayout Movie;
        private RatingBar MovieRating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MovieTitle=itemView.findViewById(R.id.MovieTitleRc);
            MovieRating=itemView.findViewById(R.id.movierating_home_rc);
            MovieReleaseDate=itemView.findViewById(R.id.MovieReleaseDateRc);
            MovieLanguage=itemView.findViewById(R.id.MovieLanguageRc);
            MoviePoster=itemView.findViewById(R.id.MovieImageRc);
            Movie=itemView.findViewById(R.id.movie);

            Movie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    MovieListResult.ResultsBean currentmovie=AllMovie.get(position);
                    int id=currentmovie.getId();

                    Intent intent=new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("Movieid",id);
                    context.startActivity(intent);
                }
            });


        }
    }
}
