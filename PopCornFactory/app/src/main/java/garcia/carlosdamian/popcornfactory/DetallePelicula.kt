package garcia.carlosdamian.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        val iv_pelicula_imagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc: TextView = findViewById(R.id.tv_pelicula_desc)
        val btnSeleccionarAsientos: Button = findViewById(R.id.btn_seleccionar_asientos)

        val bundle = intent.extras
        var movieTitle: String? = null

        if (bundle != null) {
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.text = bundle.getString("titulo")
            tv_pelicula_desc.text = bundle.getString("sinopsis")
            movieTitle = bundle.getString("titulo") // Obtener el título para pasarlo a SeatSelection
        }

        btnSeleccionarAsientos.setOnClickListener {
            if (movieTitle != null) {
                val intent = Intent(this, SeatSelection::class.java)
                intent.putExtra("titulo", movieTitle) // Pasar el título de la película
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error: No se pudo cargar el título de la película.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}