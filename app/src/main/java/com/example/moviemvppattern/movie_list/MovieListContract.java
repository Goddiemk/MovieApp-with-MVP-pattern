package com.example.moviemvppattern.movie_list;

import com.example.moviemvppattern.model.Movie;
import com.example.moviemvppattern.model.MoviesResponse;

import java.util.List;

import io.reactivex.Single;

public interface MovieListContract {

    interface Model {

        Single<MoviesResponse> getMovieList();

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Movie> movieArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
