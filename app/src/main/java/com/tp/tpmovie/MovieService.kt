package com.tp.tpmovie

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tp.tpmovie.model.Movie
import com.tp.tpmovie.model.Person
import com.tp.tpmovie.model.ResponseMetier
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieService {

    companion object{
        // La racine de l'api
        val BASE_URL = "http://127.0.0.1:3000/"

        // L'utilitaire conversion JSON <=> Objet
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

        // Retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build();
    }

    @POST("login")
    suspend fun login(@Body person: Person) : ResponseMetier<String>

    @POST("signup")
    suspend fun signup(@Body person: Person) : ResponseMetier<Person>

    @POST("reset-password")
    suspend fun resetPassword(@Body person: Person) : ResponseMetier<Boolean>

    @GET("movies")
    suspend fun getMovies() : ResponseMetier<List<Movie>>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id : Int) : ResponseMetier<Movie>

    @POST("movies")
    suspend fun saveMovie(@Body data: Movie) : ResponseMetier<Movie>

    @GET("verify-token")
    suspend fun verifyToken(@Header("authorization") token :String) : ResponseMetier<Boolean>

    object MovieApi {
        val retrofitService : MovieService by lazy { retrofit.create(MovieService::class.java) }
    }

}