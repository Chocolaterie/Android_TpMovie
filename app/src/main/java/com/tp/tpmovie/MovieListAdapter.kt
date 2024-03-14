package com.example.demoeni

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tp.tpmovie.MovieDetailActivity
import com.tp.tpmovie.MovieFormActivity
import com.tp.tpmovie.databinding.CellMovieBinding
import com.tp.tpmovie.viewmodel.AuthContextViewModel
import com.tp.tpmovie.model.Movie

class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        holder.bind(movie);
    }

    /**
     * Classe interne (inception)
     * Permet de determiner comment on charge/connecter/liée les données avec la cellule
     */
    class ViewHolder(val myParent : ViewGroup, val binding : CellMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Faire le lien avec une donnée envoyé et la vue
         */
        fun bind(data : Movie) {
            binding.movie = data;
            // instancier le authcontext
            val authContextViewModel = AuthContextViewModel()
            binding.authContext = authContextViewModel;

            // Ecouter le click du bouton view dans la cellule
            binding.btnViewMovie.setOnClickListener {
                // Ouvrir une page avec le film de la cellule
                val intent = Intent(binding.root.context, MovieDetailActivity::class.java);

                intent.putExtra("movie", data);

                binding.root.context.startActivity(intent)
            }

            // Modifier
            binding.btnEditMovie.setOnClickListener {
                // Ouvrir une page avec le film de la cellule
                val intent = Intent(binding.root.context, MovieFormActivity::class.java);

                intent.putExtra("movie", data);

                binding.root.context.startActivity(intent)
            }

            // Experiemntal : charger url sur la cellule
            Picasso.get().load(data.thumbnail_url).into(binding.ivMovieCover);

            authContextViewModel.getAuthRegistry()?.bLogged?.observe(myParent?.context as LifecycleOwner, Observer {
                binding.authContext =  binding.authContext;
            })

            binding.executePendingBindings();
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CellMovieBinding.inflate(layoutInflater, parent, false);

                return ViewHolder(parent, binding);
            }
        }
    }
}