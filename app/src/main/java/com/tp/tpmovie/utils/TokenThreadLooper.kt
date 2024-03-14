package com.tp.tpmovie.utils

import com.tp.tpmovie.MovieService
import com.tp.tpmovie.model.ResponseMetier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TokenThreadLooper {

    /**
     * Pour savoir si le thread customisé est lancé
     */
    var isRunning = false;

    fun start(interval : Long){
        if (!isRunning){
            isRunning = true;

            GlobalScope.launch {
                while (isRunning) {
                    // Attendre x interval
                    delay(interval);

                    // Tester que le token est valide
                    val token = AuthRegistry.getAuthInstance()?.token;
                    val response = MovieService.MovieApi.retrofitService.verifyToken(token!!);

                    // Si n'est plus valide
                    if (response.code != "200") {
                        // Arreter le thread
                        stop();
                        // Se déconnecter
                        AuthRegistry.getAuthInstance()?.logout();
                    }
                }
            }

        }
    }

    fun stop(){
        isRunning = false;
    }
}