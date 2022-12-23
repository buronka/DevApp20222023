package fr.buronka.projet.adapter

import android.media.ImageReader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.buronka.projet.R

class ImageAdapter(private val layoutId: Int) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    //composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //image
        val imageRecup = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    //met à jour chaque modele avec l'image en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    //renvoie le nombre d'items afficher dynamiquement
    override fun getItemCount(): Int {
        return 5
    }


}