package com.example.moviemvppattern.movie_list;

import android.util.Log;

import com.example.moviemvppattern.model.Movie;
import com.example.moviemvppattern.model.MoviesResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter implements MovieListContract.Presenter {

    private final String TAG = MovieListPresenter.class.getSimpleName();
    private MovieListContract.View movieListView;
    private MovieListModel movieListModel;

    @Inject
    public MovieListPresenter(MovieListModel movieListModel, MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        this.movieListModel = movieListModel;
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void requestDataFromServer() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesResponse moviesResponse) {
                        List<Movie> movies = moviesResponse.getMovies();
                        movieListView.setDataToRecyclerView(movies);
                        if (movieListView != null) {
                            movieListView.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        movieListView.onResponseFailure(e);
                        if (movieListView != null) {
                            movieListView.hideProgress();
                        }
                    }
                });
    }

    @Override
    public void getMoreData(int pageNo) {

    }
}
