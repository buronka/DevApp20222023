package fr.buronka.projet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.buronka.projet.ImageModel
import fr.buronka.projet.ImageRepository.Singleton.imageList
import fr.buronka.projet.MainActivity
import fr.buronka.projet.R
import fr.buronka.projet.adapter.ImageAdapter
import fr.buronka.projet.adapter.ImageItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //imageList dans le singleton utilisé à la place de celui la
        /*
        //créer liste qui stock
        val imageList = arrayListOf<ImageModel>()
        */

        //liste elements en local
        /*
        //enregistrer premiere image dans liste
        imageList.add(ImageModel(
            "Image 1",
            "description image1",
            "https://mangadex.org/covers/6a468761-5bd6-4de0-a0cb-47cb456ac2e0/6fede191-79a0-453b-8b8d-939ab067f657.jpg", //mettre lien URL de l'image ici
            false
        ))

        //enregistrer seconde image dans liste
        imageList.add(ImageModel(
            "Image 2",
            "description image2",
            "https://mangadex.org/covers/801513ba-a712-498c-8f57-cae55b38cc92/2a61abcb-8e6e-460d-8551-1caa93e09e39.jpg", //mettre lien URL de l'image ici
            false
        ))

        //enregistrer troisieme image dans liste
        imageList.add(ImageModel(
            "Image 3",
            "description image3",
            "https://mangadex.org/covers/a77742b1-befd-49a4-bff5-1ad4e6b0ef7b/351cac60-4f6f-4548-95be-3189c9705c76.png", //mettre lien URL de l'image ici
            false
        ))

        //enregistrer quatrieme image dans liste
        imageList.add(ImageModel(
            "Image 4",
            "description image4",
            "https://mangadex.org/covers/2e0fdb3b-632c-4f8f-a311-5b56952db647/effc6829-f790-42ad-a174-ced203920599.jpg", //mettre lien URL de l'image ici
            false
        ))
        */

        //récup recycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ImageAdapter(context, imageList, R.layout.item_horizontal_image)

        //recup du second recycler view
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ImageAdapter(context, imageList, R.layout.item_vertical_image)
        verticalRecyclerView.addItemDecoration(ImageItemDecoration())

        return view
    }
}