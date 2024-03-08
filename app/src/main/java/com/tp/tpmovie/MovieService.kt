package com.tp.tpmovie

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tp.tpmovie.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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

    @GET("movies")
    suspend fun getMovies() : List<Movie>

    @GET("movies/{id}")
    suspend fun getMovie(id : Int) : Movie

    @POST("movies")
    suspend fun saveMovie(@Body data: Movie) : Movie

    object MovieApi {
        val retrofitService : MovieService by lazy { retrofit.create(MovieService::class.java) }
    }

}