package com.tp.tpmovie

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login);
    }

    fun onSubmit(view: View){
        // Preparer une dialog box
        var builder = AlertDialog.Builder(this);
        builder.setTitle("Connexion");
        builder.setMessage("Vous êtes connecté(e) avec succès");
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss();
        };
        builder.show();
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
