package fr.buronka.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.buronka.projet.fragments.AddImageFrgment
import fr.buronka.projet.fragments.CollectionFragment
import fr.buronka.projet.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load_fragment(HomeFragment(this),R.string.home_page_title)

        // Ajout de la barre de navigation
        val barrenav = findViewById<BottomNavigationView>(R.id.navigation_view)
        barrenav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_page -> {
                    load_fragment(HomeFragment(this),R.string.home_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.add_page -> {
                    load_fragment(AddImageFrgment(this),R.string.add_image_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.coll_page -> {
                    load_fragment(CollectionFragment(this),R.string.collection_page_title)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }

        }

        load_fragment(HomeFragment(this),R.string.home_page_title)


    }

    private fun load_fragment(fragment: Fragment, string: Int) {
        //charger ImageRepository
        val repo = ImageRepository()

        //actualiser titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        //maj liste images
        repo.updateData {
            //injecter le fragment dans notre boite (fragment container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}