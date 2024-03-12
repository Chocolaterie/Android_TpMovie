package com.tp.tpmovie.utils

class AuthRegistry(var token : String) {

    fun setValidToken(_token : String){
        token = _token;
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