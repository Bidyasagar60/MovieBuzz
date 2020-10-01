package com.knight.moviebuzz.AdapterHouse;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knight.moviebuzz.View.MovieDetailActivity;
import com.knight.moviebuzz.Model.Pojo.SimilarMovieResult;
import com.knight.moviebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder> {

    private Activity context;
    private List<SimilarMovieResult.ResultsBean> AllMovie;


    public SimilarMoviesAdapter(Activity context, List<SimilarMovieResult.ResultsBean> allMovie) {
        this.context = context;
        AllMovie = allMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.similar_movie_view,parent,false);
        return new SimilarMoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        SimilarMovieResult.ResultsBean movie=AllMovie.get(position);


        String image_url="https://image.tmdb.org/t/p/w500/"+movie.getPoster_path();

        Picasso.get().load(image_url).into(holder.Poster);

    }

    @Override
    public int getItemCount() {
        return AllMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView Poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Poster=itemView.findViewById(R.id.Movie_Poster_Rc);
             Poster.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int position=getAdapterPosition();
                    SimilarMovieResult.ResultsBean movie=AllMovie.get(position);
                     int id=movie.getId();

                     Intent intent = new Intent(context,MovieDetailActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                     intent.putExtra("Movieid",id);
                     context.finish();
                     context.startActivity(intent);

                 }
             });
        }
    }
}
