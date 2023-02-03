package fr.buronka.projet.adapter

import android.media.ImageReader
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.buronka.projet.ImageModel
import fr.buronka.projet.MainActivity
import fr.buronka.projet.R

class ImageAdapter(private val context: MainActivity,
                   private val imageList: List<ImageModel>,
                   private val layoutId: Int) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    //composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //image
        val imageRecup = view.findViewById<ImageView>(R.id.image_item)
        val imageName:TextView? = view.findViewById(R.id.name_item)
        val imageDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    //met à jour chaque modele avec l'image en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récup info de image
        val currentImage = imageList[position]

        //utiliser glide pour recup image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentImage.imageUrl)).into(holder.imageRecup)

        //maj nom image
        holder.imageName?.text = currentImage.name

        //maj description image
        holder.imageDescription?.text = currentImage.description

        //verif si image fav ou non
        if(currentImage.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_fav)
        }
        else {
            holder.starIcon.setImageResource(R.drawable.ic_unfav)
        }
    }

    //renvoie le nombre d'items afficher dynamiquement
    override fun getItemCount(): Int = imageList.size


}