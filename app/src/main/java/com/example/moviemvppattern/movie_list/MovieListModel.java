package com.example.moviemvppattern.movie_list;

import android.util.Log;

import com.example.moviemvppattern.model.MoviesResponse;
import com.example.moviemvppattern.model.Movie;
import com.example.moviemvppattern.network.ApiService;

import static com.example.moviemvppattern.network.ApiClient.API_KEY;
import static com.example.moviemvppattern.network.ApiClient.LANGUAGE;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener) {
        ApiService.getApiService()
                .getPopularMovies(API_KEY, LANGUAGE, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesResponse moviesResponse) {
                        List<Movie> movies = moviesResponse.getMovies();
                        Log.d(TAG, "Number of movies received: " + movies.size());
                        onFinishedListener.onFinished(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        onFinishedListener.onFailure(e);
                    }
                });
    }
}
