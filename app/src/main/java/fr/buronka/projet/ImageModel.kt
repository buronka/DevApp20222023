package fr.buronka.projet

class ImageModel(
    val date : String = "Date prise photo",
    val heure : String = "Heure prise photo",
    val imageUrl: String = "http://buronka.com/imagefaceface.jpg",
    var resolved : Boolean = false,
    val id : String = "image ID"
)

//class ImageModel(
//    val name: String = "Image face à face",
//    val description: String = "Description image",
//    val imageUrl: String = "http://buronka.com/imagefaceface.jpg",
//    val liked: Boolean = false
//)