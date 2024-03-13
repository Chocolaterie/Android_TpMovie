package com.tp.tpmovie.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(var context : Context, var person: Person = Person()) : ViewModel() {

    fun onSubmit(){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(context, "Envoie du mail");

        viewModelScope.launch {
            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.resetPassword(person);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200" || response.code == "201"){
                // Afficher message
                Helpers.showAlert(context, "Un mail vous à été envoyé")
            }
            else {
                // Afficher message
                Helpers.showAlert(context, "Serveur inaccessible")
            }
        }
    }
}