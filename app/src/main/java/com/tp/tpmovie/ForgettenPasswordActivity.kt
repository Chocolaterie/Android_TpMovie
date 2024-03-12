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
import com.tp.tpmovie.databinding.ActivityForgotPasswordBinding
import com.tp.tpmovie.ui.theme.TpMovieTheme
import com.tp.tpmovie.utils.Helpers
import com.tp.tpmovie.viewmodel.ForgetPasswordViewModel
import kotlinx.coroutines.launch

class ForgettenPasswordActivity : ComponentActivity() {

    lateinit var vm : ActivityForgotPasswordBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        vm.viewModel = ForgetPasswordViewModel();
    }

    fun onSubmit(view: View){
        // Afficher une boite de chargement
        Helpers.showProgressDialog(this, "Envoie du mail");

        lifecycleScope.launch {

            // Récupérer la réponse métier de l'api
            val response = MovieService.MovieApi.retrofitService.resetPassword(vm.viewModel?.person!!);

            // Toujours fermer la boite de chargement
            Helpers.closeProgressDialog();

            // Si connexion avec succès
            if (response.code == "200" || response.code == "201"){
                // Afficher message
                var builder = AlertDialog.Builder(this@ForgettenPasswordActivity);
                builder.setTitle("Récuperation de mot passe");
                builder.setMessage("Un mail vous a été envoyé");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }
            else {
                // Afficher message
                var builder = AlertDialog.Builder(this@ForgettenPasswordActivity);
                builder.setTitle("Récuperation de mot passe");
                builder.setMessage("Serveur inaccessible");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }
        }
    }
}
