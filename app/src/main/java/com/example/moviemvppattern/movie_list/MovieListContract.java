package com.example.moviemvppattern.movie_list;

import com.example.moviemvppattern.model.Movie;

import java.util.List;

public interface MovieListContract {
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Movie> movies);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener);
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
