package com.tp.tpmovie.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.tpmovie.ForgettenPasswordActivity
import com.tp.tpmovie.MovieListActivity
import com.tp.tpmovie.MovieService
import com.tp.tpmovie.RegisterActivity
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.AuthRegistry
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginViewModel(var context: Context, var person: Person) : ViewModel() {

    fun callLoginApi(){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(context, "Tentative de connexion...");

        viewModelScope.launch {

            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.login(person);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200"){
                // Garde le token
                AuthRegistry.getAuthInstance()?.setValidToken(response.data!!);

                // Afficher message
                Helpers.showAlert(context, ("Vous êtes connecté(e) avec succès"), {
                    // Ouvrir la page films
                    val intent = Intent(context, MovieListActivity::class.java)
                    context.startActivity(intent);
                });


            }
            else {
                // Afficher message
                Helpers.showAlert(context, ("Couple email/mot de passe invalide"));
            }
        }
    }

    fun openActivity(classType : KClass<*>) {
        var intent = Intent(context, classType.java);
        context.startActivity(intent);
    }

    /**
     * Ouvrir page RegisterActivity lors d'un clique
     */
    fun onClickRegisterNow(){
        openActivity(RegisterActivity::class);
    }

    /**
     * Ouvrir page RegisterActivity lors d'un clique
     */
    fun onClickForgetPassword(){
        openActivity(ForgettenPasswordActivity::class);
    }
}