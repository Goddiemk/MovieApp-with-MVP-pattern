package com.example.moviemvppattern.movie_list;

import com.example.moviemvppattern.model.MoviesResponse;
import com.example.moviemvppattern.network.ApiService;

import static com.example.moviemvppattern.network.ApiClient.API_KEY;
import static com.example.moviemvppattern.network.ApiClient.LANGUAGE;

import io.reactivex.Single;

public class MovieListModel implements MovieListContract.Model {

    @Override
    public Single<MoviesResponse> getMovieList() {
        return ApiService.getApiService().getPopularMovies(API_KEY, LANGUAGE, 1);
    }
}
