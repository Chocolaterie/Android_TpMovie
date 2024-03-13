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
        val forgetPasswordViewModel = ForgetPasswordViewModel(this);
        vm.viewModel = forgetPasswordViewModel;

        vm.btnSubmit.setOnClickListener {
            forgetPasswordViewModel.onSubmit();
        }
    }


}
