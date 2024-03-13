package com.tp.tpmovie.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.LoginActivity
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.launch

class RegisterViewModel(var context: Context, var person: Person = Person(), var passwordConfirmation: String = "") : ViewModel() {
    fun onSubmit(){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(context, "Tentative d'inscription...");

        viewModelScope.launch {

            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.signup(person);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200"){
                // Afficher message
                Helpers.showAlert(context, "Vous êtes inscrit(e) avec succès");

                // Ouvrir la page login
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent);
            }
            else {
                // Afficher message
                Helpers.showAlert(context, "Erreur de l'inscription");
            }
        }
    }

}