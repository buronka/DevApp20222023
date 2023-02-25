package fr.buronka.projet.fragments


import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.buronka.projet.ImageModel
import fr.buronka.projet.ImageRepository
import fr.buronka.projet.adapter.ImageAdapter
import fr.buronka.projet.R

class PhotoPopup(
    private val adapter: ImageAdapter,
    private val currentImage: ImageModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_photo_details)
        setupComponents()
        setupCloseButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView) {
        if (currentImage.resolved) {
            button.setImageResource(R.drawable.ckeck)
        } else {
            button.setImageResource(R.drawable.hourglass)
        }
    }

    private fun setupStarButton() {
        //récupérer
        val StarButton = findViewById<ImageView>(R.id.star_button)
        updateStar(StarButton)

        // interaction
        StarButton.setOnClickListener {
            currentImage.resolved = !currentImage.resolved
            val repo = ImageRepository()
            repo.updateImage(currentImage)
            updateStar(StarButton)
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            //fermer le popup
            dismiss()
        }
    }

    private fun setupComponents() {
        //actualiser image
        val PhotoImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentImage.imageUrl)).into(PhotoImage)

        //actualiser date
        findViewById<TextView>(R.id.popup_image_date).text = currentImage.date

        //actualiser heure
        findViewById<TextView>(R.id.popup_image_heure).text = currentImage.heure
    }

}