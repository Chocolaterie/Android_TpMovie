package com.tp.tpmovie.viewmodel

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.MovieDetailActivity
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.Movie
import kotlinx.coroutines.launch

class MovieFormViewModel(val context : Context, var movie : Movie = Movie()) : ViewModel() {

    fun SaveMovie(){

        // Afficher une loading modal avant d'appeler le service
        val progressDialog = ProgressDialog(context);
        progressDialog.setTitle("Chargement");
        progressDialog.setMessage("Sauvegarde du film..");
        progressDialog.show();

        viewModelScope.launch {

            val movieUpdated = MovieService.MovieApi.retrofitService.saveMovie(movie).data!!;

            // Fermer le progress
            progressDialog.dismiss();

            // Ouvrir une page avec le film de la cellule
            val intent = Intent(context, MovieDetailActivity::class.java);

            intent.putExtra("movie", movieUpdated);

            context.startActivity(intent)
        }
    }
}