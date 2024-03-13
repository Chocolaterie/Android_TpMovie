package com.tp.tpmovie

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.MovieListAdapter
import com.tp.tpmovie.databinding.ActivityMovieListBinding
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.utils.Helpers
import com.tp.tpmovie.viewmodel.MovieListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MovieListActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieListBinding;

    lateinit var adapter : MovieListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        var movieListViewModel  = MovieListViewModel(this);
        vm.viewModel = movieListViewModel;

        // Adapter
        adapter = MovieListAdapter();
        vm.rvMovies.adapter = adapter;

        // Que faire lorsque je sait que la donnée à été modifié
        movieListViewModel.movies.observe(this, Observer {
            adapter.submitList(it);
        });

        // Appeler par défaut l'api
        movieListViewModel.callMoviesApi();
    }
}
