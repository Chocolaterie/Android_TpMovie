package com.tp.tpmovie.utils

import android.app.AlertDialog
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


        fun showAlert(context: Context, message: String, onOk : () -> Unit){
            // Afficher message
            var builder = AlertDialog.Builder(context);
            builder.setTitle("Information");
            builder.setMessage(message);
            builder.setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss();
                onOk();
            };

            builder.show();
        }

        fun showAlert(context: Context, message: String){
            // Afficher message
            var builder = AlertDialog.Builder(context);
            builder.setTitle("Information");
            builder.setMessage(message);
            builder.setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss();
            };

            builder.show();
        }
    }
}