package com.tp.tpmovie.model

class ResponseMetier<T> {

    var code: String = "";
    var message: String = "";
    var data : T? = null;
}