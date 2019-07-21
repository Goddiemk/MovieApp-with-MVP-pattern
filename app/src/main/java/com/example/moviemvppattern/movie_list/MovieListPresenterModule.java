package com.example.moviemvppattern.movie_list;

import dagger.Module;
import dagger.Provides;

@Module public class MovieListPresenterModule {

    private final MovieListContract.View view;
    private final MovieListModel model;

    public MovieListPresenterModule(MovieListContract.View view, MovieListModel model) {
        this.view = view;
        this.model = model;
    }

    @Provides
    MovieListContract.View MovieListContractView() {
        return view;
    }

    @Provides
    MovieListModel MovieListContractModel() {
        return model;
    }
}
