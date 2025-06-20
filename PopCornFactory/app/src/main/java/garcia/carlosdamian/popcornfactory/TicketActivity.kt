package garcia.carlosdamian.popcornfactory

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)

        val ticketMovieHeader: ImageView = findViewById(R.id.ticket_movie_header)
        val ticketMovieTitle: TextView = findViewById(R.id.ticket_movie_title)
        val ticketSeatInfo: TextView = findViewById(R.id.ticket_seat_info)
        val ticketDoneButton: Button = findViewById(R.id.ticket_done_button)

        val bundle = intent.extras

        if (bundle != null) {
            val movieTitle = bundle.getString("movie_title")
            val movieHeaderResId = bundle.getInt("movie_header_res_id", 0)
            val selectedSeatTag = bundle.getString("selected_seat_tag")

            if (movieHeaderResId != 0) {
                ticketMovieHeader.setImageResource(movieHeaderResId)
            }
            ticketMovieTitle.text = movieTitle
            ticketSeatInfo.text = selectedSeatTag
        }

        ticketDoneButton.setOnClickListener {

            finish() // Cierra esta actividad y regresa a la anterior (SeatSelection, o la que la llamó)

        }
    }
}