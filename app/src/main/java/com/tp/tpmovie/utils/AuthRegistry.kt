package com.tp.tpmovie.utils

import androidx.lifecycle.MutableLiveData

class AuthRegistry(var token : String, var bLogged : MutableLiveData<Boolean> = MutableLiveData(false)) {

    val tokenThreadLooper = TokenThreadLooper();

    fun setValidToken(_token : String){
        token = _token;

        // notifié connecté
        bLogged.value = true;

        // lancer le thread qui va tester tout x moment que le token est valide
        tokenThreadLooper.start(5000);
    }

    fun logout(){
        token = "";
        // notifié décconnecté
        //bLogged.value = false;
        bLogged.postValue(false)
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