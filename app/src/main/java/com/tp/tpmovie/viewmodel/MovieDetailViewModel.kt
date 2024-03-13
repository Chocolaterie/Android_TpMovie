package com.tp.tpmovie.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailViewModel(val context: Context, val idMovie : Int, var movie: MutableLiveData<Movie> = MutableLiveData<Movie>()) : ViewModel() {

    fun callApi(){
        // Afficher une loading modal avant d'appeler le service
        Helpers.showProgressDialog(context, "Récupérer du film...");

        // Lancer par défaut le service pour récupérer les films
        viewModelScope.launch {
            delay(1000);

            // Récupérer le film
            val response = MovieService.MovieApi.retrofitService.getMovie(idMovie);
            movie.value = response.data!!;

            // Fermer le loading modal
            Helpers.closeProgressDialog();
        }
    }
}