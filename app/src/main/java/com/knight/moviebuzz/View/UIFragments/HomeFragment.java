package com.knight.moviebuzz.View.UIFragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.knight.moviebuzz.AdapterHouse.MovieListAdapter;
import com.knight.moviebuzz.Model.Clients.MovieBuzzClient;
import com.knight.moviebuzz.Model.Pojo.MovieListResult;
import com.knight.moviebuzz.R;
import com.knight.moviebuzz.Model.Api.PopularMovieApi;
import com.knight.moviebuzz.Utility.Constants;
import com.knight.moviebuzz.ViewModel.PopularMoviesViewModel;

import java.util.List;





public class HomeFragment extends Fragment {


    private RecyclerView MovieListView;
    private MovieListAdapter movieListAdapter;
    private TextView Title;
    private PopularMoviesViewModel popularMoviesViewModel;
    private Handler handler=new Handler();
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View homeview = inflater.inflate(R.layout.fragment_home, container, false);

        Constants.KEY_STATUS=Constants.KEY_LOADING;
        MovieListView=homeview.findViewById(R.id.AllMovieList);
        MovieListView.setHasFixedSize(true);
        Title=homeview.findViewById(R.id.title);
        progressBar=homeview.findViewById(R.id.progressbar);
        Title.setText("Popular Movies");

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        MovieListView.setLayoutManager(layoutManager);



        popularMoviesViewModel= ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        popularMoviesViewModel.init();
        popularMoviesViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {

                List<MovieListResult.ResultsBean> MovieList= movieListResult.getResults();
                movieListAdapter=new MovieListAdapter(getActivity(),MovieList);
                MovieListView.setAdapter(movieListAdapter);
                movieListAdapter.notifyDataSetChanged();





            }
        });






        return homeview;
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
            Log.d("ZTAG", "run: "+Constants.KEY_STATUS);

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