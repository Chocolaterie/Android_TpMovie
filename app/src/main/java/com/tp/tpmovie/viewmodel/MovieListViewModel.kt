package com.tp.tpmovie.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieListViewModel(val context : Context, var movies : MutableLiveData<List<Movie>> = MutableLiveData()) : ViewModel() {

    fun callMoviesApi(){
        // Afficher une loading modal avant d'appeler le service
        Helpers.showProgressDialog(context, "Récupérer des films...");

        // Lancer par défaut le service pour récupérer les films
        viewModelScope.launch {
            delay(1000);

            // Notifier les changements
            movies.value = MovieService.MovieApi.retrofitService.getMovies().data!!;

            // Fermer le loading modal
            Helpers.closeProgressDialog();
        }
    }
}