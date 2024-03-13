package com.tp.tpmovie

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tp.tpmovie.databinding.ActivityHomeBinding
import com.tp.tpmovie.databinding.ActivityLoginBinding
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.utils.AuthRegistry
import com.tp.tpmovie.utils.Helpers
import com.tp.tpmovie.viewmodel.AuthContextViewModel
import com.tp.tpmovie.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeActivity : ComponentActivity() {

    lateinit var vm : ActivityHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_home);
        val authContextViewModel = ViewModelProvider(this).get(AuthContextViewModel::class.java);
        vm.authContext = authContextViewModel;

        // Ecouter quand je me connecte -> rafraichir la pahe
        authContextViewModel.getAuthRegistry()?.bLogged!!.observe(this, Observer {
            vm.authContext =  vm.authContext;
        })

        // Les cliques
        vm.btnLogging.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java));
        }

        vm.btnLogout.setOnClickListener {
            authContextViewModel.getAuthRegistry()?.logout();
        }

        vm.btnShowMovies.setOnClickListener {
            startActivity(Intent(this, MovieListActivity::class.java));
        }

        // TODO
        vm.btnEditUser.setOnClickListener {

        }

        // TODO
        vm.btnCreateMovie.setOnClickListener {

        }
    }

}
