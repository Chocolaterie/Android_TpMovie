package com.tp.tpmovie.model

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.ViewModel
import com.tp.tpmovie.utils.AuthRegistry

class AuthContextViewModel : ViewModel() {

    fun isLogged() : Boolean {
        return AuthRegistry.getAuthInstance()?.tokenExist()!!;
    }

    fun isLoggedVisibility() : Int {
        if (isLogged()){
            return View.VISIBLE;
        }
        return View.INVISIBLE;
    }
}