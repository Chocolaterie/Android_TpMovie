package com.tp.tpmovie.viewmodel

import com.tp.tpmovie.model.Person

class RegisterViewModel(var person: Person = Person(), var passwordConfirmation: String = "") {


    fun fakeData(){
        person.pseudo = "IsaacTelephoneQuiSonne";
        person.email = "IsaacBenDorianVivianne@gmail.com";
        person.password = "123456";
    }

}