package com.tp.tpmovie.utils

import androidx.lifecycle.MutableLiveData

class AuthRegistry(var token : String, var bLogged : MutableLiveData<Boolean> = MutableLiveData(false)) {

    fun setValidToken(_token : String){
        token = _token;
        // notifié connecté
        bLogged.value = true;
    }

    fun logout(){
        token = "";
        // notifié décconnecté
        bLogged.value = false;
    }

    fun tokenExist() : Boolean {
        return (token != "");
    }

    companion object {

        var instance : AuthRegistry? = null;

        fun getAuthInstance() : AuthRegistry? {
            if (instance != null) {
                return instance;
            }
            instance = AuthRegistry("");
            return instance;
        }
    }
}