package com.tp.tpmovie.model

class Movie (var id : Int = -1, var title: String = "", var synopsis: String = "", var duration : String = "", var year : String = "") {

    override fun equals(other: Any?): Boolean {
        if (other is Movie){
            return title == other.title;
        }
        return super.equals(other)
    }
}