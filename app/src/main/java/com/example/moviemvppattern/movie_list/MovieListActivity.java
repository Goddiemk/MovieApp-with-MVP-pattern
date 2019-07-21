package com.example.moviemvppattern.movie_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviemvppattern.R;
import com.example.moviemvppattern.adapter.MoviesAdapter;
import com.example.moviemvppattern.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    private static final String TAG = "MovieListActivity";
    private List<Movie> moviesList;
    private MoviesAdapter adapter;
    private MovieListPresenter movieListPresenter;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        //Initializing presenter
        movieListPresenter = new MovieListPresenter(this);

        movieListPresenter.requestDataFromServer();
    }

    private void initUI() {
        RecyclerView rvMovieList = findViewById(R.id.movies_list);
        moviesList = new ArrayList<>();

        adapter = new MoviesAdapter(moviesList);
        rvMovieList.setAdapter(adapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));

        pbLoading = findViewById(R.id.pb_loading);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieArrayList) {
        moviesList.addAll(movieArrayList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
    }
}
