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
import com.knight.moviebuzz.ViewModel.UpcomingMoviesViewModel;

import java.util.List;


public class UpcomingMovieFragment extends Fragment {

    private TextView Title;
    private RecyclerView MovieListView;
    private MovieListAdapter movieListAdapter;
    private UpcomingMoviesViewModel upcomingMoviesViewModel;
    private ProgressBar progressBar;
    private Handler handler=new Handler();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View upcoming_movies_view = inflater.inflate(R.layout.fragment_home, container, false);

        Title=upcoming_movies_view.findViewById(R.id.title);
        Title.setText("Upcoming Movies");
        progressBar=upcoming_movies_view.findViewById(R.id.progressbar);

        MovieListView=upcoming_movies_view.findViewById(R.id.AllMovieList);
        MovieListView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        MovieListView.setLayoutManager(layoutManager);

        upcomingMoviesViewModel= ViewModelProviders.of(this).get(UpcomingMoviesViewModel.class);
        upcomingMoviesViewModel.init();
        upcomingMoviesViewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {




                List<MovieListResult.ResultsBean> movies= movieListResult.getResults();

                movieListAdapter=new MovieListAdapter(getContext(),movies);
                MovieListView.setAdapter(movieListAdapter);
                movieListAdapter.notifyDataSetChanged();

            }
        });




        return upcoming_movies_view;
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