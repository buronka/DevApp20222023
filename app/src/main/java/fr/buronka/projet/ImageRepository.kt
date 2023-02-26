package fr.buronka.projet

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.buronka.projet.ImageRepository.Singleton.databaseRef
import fr.buronka.projet.ImageRepository.Singleton.downloadUri
import fr.buronka.projet.ImageRepository.Singleton.imageList
import fr.buronka.projet.ImageRepository.Singleton.storageReference
import java.util.*

class ImageRepository {
    object Singleton{
        val storageReference = FirebaseStorage.getInstance().getReference("gs:projetdevapp20222023.appspot.com")

        //se connecter a la reference "images"
        val databaseRef = FirebaseDatabase.getInstance().getReference("images")

        //creer une liste qui va contenir nos images
        val imageList = arrayListOf<ImageModel>()

        // Lien image actuelle
        var downloadUri: Uri? = null
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

    // Envoi fichier au Storage
    fun uploadImage(file : Uri, callback: () -> Unit) {
        // Not Null
        if(file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {task ->
                if(!task.isSuccessful) {
                    task.exception?.let {throw it}
                }
                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    downloadUri = task.result
                    callback()
                }
            }
        }

    }

    //mettre a jour dans la bdd
    fun updateImage(Image: ImageModel) {
        databaseRef.child(Image.id).setValue(Image)
    }

    fun insertImage(Image: ImageModel) {
        databaseRef.child(Image.id).setValue(Image)
    }


}