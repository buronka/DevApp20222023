package fr.buronka.projet.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import fr.buronka.projet.MainActivity
import fr.buronka.projet.R

class AddImageFrgment (
    private val context:MainActivity
) : Fragment() {

    //private var uploadedImage:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_image, container, false)

        //uploadedImage = view.findViewById(R.id.preview_img)

        // Chargement image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)
        //pickupImageButton.setOnClickListener { pickupImage() }

        return view
    }
/*
    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 69)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        if(requestCode == 69 && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null) return
            val selectedImage = data.data
        }
    }
 */
}
