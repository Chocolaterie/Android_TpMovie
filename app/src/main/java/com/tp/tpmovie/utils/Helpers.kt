package com.tp.tpmovie.utils

import android.app.ProgressDialog
import android.content.Context

class Helpers {

    companion object {

        /**
         * Pointer mémoire de la boite de chargement actuellement
         */
        var progressDialog : ProgressDialog? = null;

        /**
         * Afficher la boite de chargement actuelle
         */
        fun showProgressDialog(_context: Context, _message : String){
            progressDialog = ProgressDialog(_context);
            progressDialog?.setTitle("Chargement");
            progressDialog?.setMessage(_message);
            progressDialog?.show();
        }

        /**
         * Fermer la boite de chargement
         */
        fun closeProgressDialog(){
            progressDialog?.dismiss();
        }
    }
}