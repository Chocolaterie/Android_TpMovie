package com.tp.tpmovie

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.tp.tpmovie.databinding.ActivityMovieDetailBinding
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.ui.theme.TpMovieTheme
import com.tp.tpmovie.viewmodel.MovieDetailViewModel
import com.tp.tpmovie.viewmodel.RegisterViewModel

class MovieDetailActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // récupérer le film envoyé depuis l'intent
        val movie = intent.getParcelableExtra<Movie>("movie");

        // Databinding view model
        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        val movieDetailViewModel = MovieDetailViewModel(this, movie!!.id);
        vm.viewModel = movieDetailViewModel;

        // Charger l'image
        Picasso.get().load(movie?.thumbnail_url).into(vm.ivMovieDetailCover);

        // Quand changement -> rafraichir le view model
        movieDetailViewModel.movie.observe(this, Observer {
            vm.viewModel = vm.viewModel;
        });

        // Appeler l'api
        movieDetailViewModel.callApi();
    }
}
