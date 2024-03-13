package com.tp.tpmovie

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.tp.tpmovie.databinding.ActivityLoginBinding
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.AuthRegistry
import com.tp.tpmovie.utils.Helpers
import com.tp.tpmovie.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginActivity : ComponentActivity() {

    lateinit var vm : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_login);

        var loginViewModel = LoginViewModel(this, Person("Viviane","viviane@gmail.com", "123456"));
        vm.viewModel = loginViewModel;

        // Lien avec les cliques
        vm.btnSubmit.setOnClickListener {
            loginViewModel.callLoginApi();
        }

        vm.tvForgetPassword.setOnClickListener {
            loginViewModel.onClickForgetPassword();
        }

        vm.tvRegisterNow.setOnClickListener {
            loginViewModel.onClickRegisterNow();
        }

    }

}
