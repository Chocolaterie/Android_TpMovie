package com.tp.tpmovie

import android.app.AlertDialog
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
import com.tp.tpmovie.databinding.ActivityRegisterBinding
import com.tp.tpmovie.ui.theme.TpMovieTheme
import com.tp.tpmovie.utils.AuthRegistry
import com.tp.tpmovie.utils.Helpers
import com.tp.tpmovie.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterActivity : ComponentActivity() {

    lateinit var vm : ActivityRegisterBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_register);

        val registry = RegisterViewModel();
        vm.viewModel = registry;

        // POUR LE TEST (LE FLEMME)
        registry.fakeData();
    }

    fun onSubmit(view: View){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(this, "Tentative d'inscription...");

        lifecycleScope.launch {

            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.signup(vm.viewModel?.person!!);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200"){
                // Afficher message
                var builder = AlertDialog.Builder(this@RegisterActivity);
                builder.setTitle("Inscription");
                builder.setMessage("Vous êtes inscrit(e) avec succès");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();

                // Ouvrir la page login
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent);
            }
            else {
                // Afficher message
                var builder = AlertDialog.Builder(this@RegisterActivity);
                builder.setTitle("Inscription");
                builder.setMessage("Erreur de l'inscription");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }
        }
    }
}
