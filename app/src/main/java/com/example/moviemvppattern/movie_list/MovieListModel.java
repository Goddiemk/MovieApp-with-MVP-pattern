package com.example.moviemvppattern.movie_list;

import android.util.Log;

import com.example.moviemvppattern.model.MoviesResponse;
import com.example.moviemvppattern.model.Movie;
import com.example.moviemvppattern.network.ApiClient;
import com.example.moviemvppattern.network.ApiInterface;

import java.util.List;

import static com.example.moviemvppattern.network.ApiClient.API_KEY;
import static com.example.moviemvppattern.network.ApiClient.LANGUAGE;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY, LANGUAGE, 1);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getMovies();
                Log.d(TAG, "Number of movies received: " + movies.size());
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
