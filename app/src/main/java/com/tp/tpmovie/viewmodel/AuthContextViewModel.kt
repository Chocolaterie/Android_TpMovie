package com.tp.tpmovie.viewmodel

import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tp.tpmovie.utils.AuthRegistry

class AuthContextViewModel() : ViewModel() {

    fun isLogged() : Boolean {
        return AuthRegistry.getAuthInstance()?.tokenExist()!!;
    }

    fun getAuthRegistry() : AuthRegistry? {
        return AuthRegistry.getAuthInstance()
    }

    fun isLoggedVisibility(inverse : Boolean) : Int {
        if (isLogged()){
            return if (inverse) View.GONE else View.VISIBLE
        }
        return if (inverse) View.VISIBLE else View.GONE

    }
}