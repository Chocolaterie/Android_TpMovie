package com.tp.tpmovie

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.tp.tpmovie.databinding.ActivityLoginBinding
import com.tp.tpmovie.databinding.ActivityMovieListBinding
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.AuthRegistry
import com.tp.tpmovie.utils.Helpers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {

    lateinit var vm : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_login);

        vm.person = Person();
    }

    fun onSubmit(view: View){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(this, "Tentative de connexion...");

        lifecycleScope.launch {

            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.login(vm.person!!);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200"){
                // Garde le token
                AuthRegistry.getAuthInstance()?.setValidToken(response.data!!);

                // Afficher message
                var builder = AlertDialog.Builder(this@MainActivity);
                builder.setTitle("Connexion");
                builder.setMessage("Vous êtes connecté(e) avec succès");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }
            else {
                // Afficher message
                var builder = AlertDialog.Builder(baseContext);
                builder.setTitle("Connexion");
                builder.setMessage("Couple email/mot de passe invalide");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }
        }
    }

    fun openActivity(classType : KClass<*>) {
        var intent = Intent(this, classType.java);
        startActivity(intent);
    }

    /**
     * Ouvrir page RegisterActivity lors d'un clique
     */
    fun onClickRegisterNow(view : View){
        openActivity(RegisterActivity::class);
    }

    /**
     * Ouvrir page RegisterActivity lors d'un clique
     */
    fun onClickForgetPassword(view : View){
        openActivity(ForgettenPasswordActivity::class);
    }
}
