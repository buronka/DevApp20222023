package fr.buronka.projet.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import fr.buronka.projet.ImageModel
import fr.buronka.projet.ImageRepository
import fr.buronka.projet.ImageRepository.Singleton.downloadUri
import fr.buronka.projet.MainActivity
import fr.buronka.projet.R
import java.util.*

class AddImageFrgment (
    private val context:MainActivity
) : Fragment() {

    private var fileS:Uri? = null
    private var uploadedImage:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_image, container, false)

        // Recuperer l'image
        uploadedImage = view.findViewById(R.id.preview_img)

        // Chargement image en récupérant les données
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        pickupImageButton.setOnClickListener { pickupImage() }

        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener { sendForm(view) }
        return view
    }

    private fun sendForm(view: View) {
        val repo = ImageRepository()
        repo.uploadImage(fileS!!) {
            val imgDate = view.findViewById<EditText>(R.id.date_input).text.toString()
            val imgHour = view.findViewById<EditText>(R.id.hour_input).text.toString()
            val downloadImageUrl = downloadUri

            // Creer un nouvel objet
            val img = ImageModel(
                date = imgDate,
                heure = imgHour,
                imageUrl = downloadImageUrl.toString(),
                resolved = false,
                id = UUID.randomUUID().toString()
            )

            // Envoi a la bdd
            repo.insertImage(img)
        }
    }

    fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val fileS = data?.data

            // Maj de l'image sur la page
            uploadedImage?.setImageURI(fileS)
        }
    }
}
