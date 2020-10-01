package com.knight.moviebuzz.Model.NetworkCallDataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.knight.moviebuzz.Model.Api.CastAndCrewApi;
import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;
import com.knight.moviebuzz.Utility.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CastAndCrewNetworkCallData {


    private CastAndCrewApi castAndCrewApi;
    private MediatorLiveData<MovieCastAndCrew> castAndCrewList;

    public CastAndCrewNetworkCallData(CastAndCrewApi castAndCrewApi) {
        this.castAndCrewApi = castAndCrewApi;
        castAndCrewList=new MediatorLiveData<>();
    }



    public void fetchMovieCastAndCrew(int movieId)
    {
        Constants.KEY_STATUS=Constants.KEY_LOADING;
        final LiveData<MovieCastAndCrew> source= LiveDataReactiveStreams.fromPublisher(castAndCrewApi.CAST_AND_CREW_List(movieId, Constants.API_KEY)
                                                  .subscribeOn(Schedulers.io())
                                                  .observeOn(AndroidSchedulers.mainThread()));

        castAndCrewList.addSource(source, new Observer<MovieCastAndCrew>() {
            @Override
            public void onChanged(MovieCastAndCrew movieCastAndCrew) {
                castAndCrewList.postValue(movieCastAndCrew);
                castAndCrewList.removeSource(source);
                Constants.KEY_STATUS=Constants.KEY_LOADING_COMPLETE;
            }
        });




    }




    public LiveData<MovieCastAndCrew> ObserveCastAndCrew()
    {
        return castAndCrewList;
    }
}
