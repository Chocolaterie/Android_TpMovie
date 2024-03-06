package com.tp.tpmovie.model

class Person (var pseudo: String = "", var email:String = "", var password: String = "", var cityCode: String = "", var city: String = "", var phone: String = "") {

    fun displayInfo() : String{
        return "Pseudo : $pseudo - $email \n $cityCode";
    }
}