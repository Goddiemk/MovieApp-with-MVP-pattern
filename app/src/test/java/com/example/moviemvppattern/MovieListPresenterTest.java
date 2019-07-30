package com.example.moviemvppattern;

import com.example.moviemvppattern.model.MoviesResponse;
import com.example.moviemvppattern.movie_list.MovieListContract;
import com.example.moviemvppattern.movie_list.MovieListModel;
import com.example.moviemvppattern.movie_list.MovieListPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class MovieListPresenterTest {
    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();

    @Mock
    private MovieListModel movieListModel;

    @Mock
    private MovieListContract.View movieListView;

    private MovieListContract.Presenter movieListPresenter;

    @Before
    public void setup() {
        movieListPresenter = new MovieListPresenter(movieListModel, movieListView);
    }

    @Test
    public void success() {
        MoviesResponse moviesResponse = new MoviesResponse(0, 0, 0, null);

        Single<MoviesResponse> moviesResponseSingle = Single.just(moviesResponse);
        when(movieListModel.getMovieList()).thenReturn(moviesResponseSingle);

        movieListPresenter.requestDataFromServer();
        verify(movieListView, never()).onResponseFailure(new Exception());

        InOrder inOrder = Mockito.inOrder(movieListView);
        inOrder.verify(movieListView, times(1)).showProgress();
        inOrder.verify(movieListView, times(1)).setDataToRecyclerView(Mockito.any());
        inOrder.verify(movieListView, times(1)).hideProgress();
    }
}