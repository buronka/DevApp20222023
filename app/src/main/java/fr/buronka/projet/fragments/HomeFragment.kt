package fr.buronka.projet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.buronka.projet.R
import fr.buronka.projet.adapter.ImageAdapter
import fr.buronka.projet.adapter.ImageItemDecoration

class HomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //récup recycler view
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ImageAdapter(R.layout.item_horizontal_image)

        //recup du second recycler view
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ImageAdapter(R.layout.item_vertical_image)
        verticalRecyclerView.addItemDecoration(ImageItemDecoration())

        return view
    }
}