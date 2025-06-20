package garcia.carlosdamian.popcornfactory

data class Pelicula(var titulo: String,
                    var image: Int,
                    var header: Int,
                    val occupiedSeats: MutableList<String> = mutableListOf(), // Lista para almacenar asientos ocupados
                    var sinopsis: String) {
}