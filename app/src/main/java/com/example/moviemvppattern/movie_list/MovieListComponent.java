package com.example.moviemvppattern.movie_list;

import dagger.Component;

@Component(modules = MovieListPresenterModule.class)
public interface MovieListComponent {

    void inject(MovieListActivity movieListActivity);
}
