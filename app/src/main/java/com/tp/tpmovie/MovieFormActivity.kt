package com.tp.tpmovie

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import com.tp.tpmovie.databinding.ActivityMovieFormBinding
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.ui.theme.TpMovieTheme
import com.tp.tpmovie.viewmodel.MovieFormViewModel
import com.tp.tpmovie.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class MovieFormActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieFormBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Récupérer le film envoyer depuis l'intent
        val movie = intent.getParcelableExtra<Movie>("movie");

        // Databind view model
        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_form);
        val movieFormViewModel = MovieFormViewModel(this, movie!!);
        vm.viewModel = movieFormViewModel;

        // Charger l'image
        Picasso.get().load(movie?.thumbnail_url).into(vm.ivMovieDetailCover);

        // Liéer le onclick
        vm.btnSubmit.setOnClickListener {
            movieFormViewModel.SaveMovie();
        }
    }
}
