package garcia.carlosdamian.popcornfactory

import android.app.Application

class MyApp : Application() {
    val peliculas: MutableList<Pelicula> = mutableListOf()
}