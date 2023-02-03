package fr.buronka.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.buronka.projet.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger ImageRepository
        val repo = ImageRepository()

        //maj liste images
        repo.updateData {
            //injecter le fragment dans notre boite (fragment container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}