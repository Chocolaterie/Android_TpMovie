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
import com.tp.tpmovie.model.Person
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

        val registerViewModel = RegisterViewModel(this, Person("isaac", "isaac@gmail.com", "123456"));
        vm.viewModel = registerViewModel;

        // Associer le clique
        vm.btnSubmit.setOnClickListener {
            registerViewModel.onSubmit();
        }
    }


}
