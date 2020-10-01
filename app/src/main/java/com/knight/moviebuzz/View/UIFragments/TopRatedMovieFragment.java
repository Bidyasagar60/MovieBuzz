package com.knight.moviebuzz.View.UIFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.knight.moviebuzz.AdapterHouse.MovieListAdapter;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.R;
import com.knight.moviebuzz.Utility.Constants;
import com.knight.moviebuzz.ViewModel.TopRatedMovieViewModel;

import java.util.List;


public class TopRatedMovieFragment extends Fragment {

    private RecyclerView MovieListView;
    private MovieListAdapter movieListAdapter;
    private TextView Title;
    private TopRatedMovieViewModel topRatedMovieViewModel;
    private ProgressBar progressBar;
    private Handler handler=new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View top_rated_movie_view = inflater.inflate(R.layout.fragment_home, container, false);


        MovieListView=top_rated_movie_view.findViewById(R.id.AllMovieList);
        MovieListView.setHasFixedSize(true);
        Title=top_rated_movie_view.findViewById(R.id.title);
        Title.setText("Top Rated Movies");

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        MovieListView.setLayoutManager(layoutManager);
        progressBar=top_rated_movie_view.findViewById(R.id.progressbar);
        handler.postDelayed(progresbarthread,0);

        topRatedMovieViewModel= ViewModelProviders.of(this).get(TopRatedMovieViewModel.class);
        topRatedMovieViewModel.init();
        topRatedMovieViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {

                List<MovieListResult.ResultsBean> movies= movieListResult.getResults();

                movieListAdapter=new MovieListAdapter(getContext(),movies);
                MovieListView.setAdapter(movieListAdapter);
                movieListAdapter.notifyDataSetChanged();


            }
        });





        return top_rated_movie_view;}


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
