package com.tp.tpmovie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (var id : Int = -1, var title: String = "", var synopsis: String = "", var duration : String = "", var year : String = "", var thumbnail_url : String = "") :
    Parcelable {

    fun displayYearAndDuration() : String{
        return "${year} - ${duration}";
    }

    override fun equals(other: Any?): Boolean {
        if (other is Movie){
            return title == other.title;
        }
        return super.equals(other)
    }
}