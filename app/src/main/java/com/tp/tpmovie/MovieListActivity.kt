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
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.MovieListAdapter
import com.tp.tpmovie.databinding.ActivityMovieListBinding
import com.tp.tpmovie.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MovieListActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieListBinding;

    lateinit var movies : List<Movie>;

    lateinit var adapter : MovieListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);

        adapter = MovieListAdapter();

        vm.rvMovies.adapter = adapter;

        // Afficher une loading modal avant d'appeler le service
        val progressDialog = ProgressDialog(this);
        progressDialog.setTitle("Chargement");
        progressDialog.setMessage("Récupérer des films...");
        progressDialog.show();

        // Lancer par défaut le service pour récupérer les films
        lifecycleScope.launch {
            delay(1000);

            // Appeler le service
            movies = MovieService.MovieApi.retrofitService.getMovies()

            // Envoyer le recyler view
            adapter.submitList(movies);

            // Fermer le loading modal
            progressDialog.dismiss();
        }
    }
}
