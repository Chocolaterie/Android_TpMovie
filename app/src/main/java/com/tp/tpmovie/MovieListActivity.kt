package com.tp.tpmovie

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.demoeni.MovieListAdapter
import com.tp.tpmovie.databinding.ActivityMovieListBinding
import com.tp.tpmovie.model.Movie
import kotlin.reflect.KClass

class MovieListActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieListBinding;

    lateinit var movies : MutableLiveData<MutableList<Movie>>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);

        var adapter = MovieListAdapter();

        vm.rvMovies.adapter = adapter;

        // faux films
        var movies = MutableLiveData<MutableList<Movie>>(mutableListOf(Movie(1, "Sharknado", "Best film", "1h36", "1994"), Movie(2, "Teletubies", "Soleil qui parle","23h36", "1999")));

        adapter.submitList(movies.value);
    }
}
