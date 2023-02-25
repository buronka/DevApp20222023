package fr.buronka.projet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.buronka.projet.MainActivity
import fr.buronka.projet.R
import fr.buronka.projet.adapter.ImageAdapter
import fr.buronka.projet.ImageRepository.Singleton.imageList

class CollectionFragment (
    private val context: MainActivity
) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)
        val coll_view = view.findViewById<RecyclerView>(R.id.coll_recycler_list)
        coll_view.adapter = ImageAdapter(context, imageList, R.layout.item_horizontal_image)
        coll_view.layoutManager = LinearLayoutManager(context)
        return view
    }
}