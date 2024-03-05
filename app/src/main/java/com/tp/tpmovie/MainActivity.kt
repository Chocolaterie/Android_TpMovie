package com.tp.tpmovie

import android.content.Context
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
import com.tp.tpmovie.ui.theme.TpMovieTheme
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login);
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
