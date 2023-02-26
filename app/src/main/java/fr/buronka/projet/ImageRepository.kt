package fr.buronka.projet

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.buronka.projet.ImageRepository.Singleton.databaseRef
import fr.buronka.projet.ImageRepository.Singleton.imageList

class ImageRepository {
    object Singleton{

        //se connecter a la reference "images"
        val databaseRef = FirebaseDatabase.getInstance().getReference("images")

        //creer une liste qui va contenir nos images
        val imageList = arrayListOf<ImageModel>()

    }

    fun updateData(callback: () -> Unit) {

        //absorber les données depuis la database -> liste d'images
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer anciennes images dans liste pour récréer nouvelle liste -> maj
                imageList.clear()

                //recolter la liste
                for (ds in snapshot.children) {
                    //construire un objet image
                    val image = ds.getValue(ImageModel::class.java)

                    //verif que image pas null -> verif que image est bien chargé
                    if(image != null) {
                        //ajouter image à notre liste
                        imageList.add(image)
                    }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    //mettre a jour dans bdd
    fun updateImage(Image: ImageModel) {
        databaseRef.child(Image.id).setValue(Image)
    }

}