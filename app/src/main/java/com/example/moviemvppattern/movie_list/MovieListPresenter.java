package com.example.moviemvppattern.movie_list;

import com.example.moviemvppattern.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MovieListPresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener {

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
        movieListModel.getMovieList(this);
    }

    @Override
    public void getMoreData(int pageNo) {

    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }
}
