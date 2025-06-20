package garcia.carlosdamian.popcornfactory

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SeatSelection : AppCompatActivity() {

    private lateinit var app: MyApp
    private lateinit var row1: RadioGroup
    private lateinit var row2: RadioGroup
    private lateinit var row3: RadioGroup
    private lateinit var row4: RadioGroup
    private lateinit var row5: RadioGroup

    private val allSeatRadioButtons = mutableListOf<RadioButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        app = application as MyApp

        val titulo = intent.getStringExtra("titulo") ?: ""
        Log.d("SeatSelectionDebug", "Movie Title received: $titulo")

        val peliculaObtenida = app.peliculas.find { it.titulo == titulo }
        Log.d("SeatSelectionDebug", "Pelicula obtained: ${peliculaObtenida?.titulo ?: "null"}")


        val title: TextView = findViewById(R.id.titleSeats)
        val bundle = intent.extras

        if (bundle != null) {
            title.text = bundle.getString("name")
        }


        row1 = findViewById(R.id.row1)
        row2 = findViewById(R.id.row2)
        row3 = findViewById(R.id.row3)
        row4 = findViewById(R.id.row4)
        row5 = findViewById(R.id.row5)


        val rows = listOf(row1, row2, row3, row4, row5)
        for ((rowIndex, _) in rows.withIndex()) {
            for (seatIndex in 1..6) {
                val seatIdName = "seat_${rowIndex + 1}_$seatIndex"
                val resId = resources.getIdentifier(seatIdName, "id", packageName)
                if (resId != 0) {
                    val radioButton = findViewById<RadioButton>(resId)
                    allSeatRadioButtons.add(radioButton)
                } else {
                    Log.e("SeatSelection", "Resource ID not found for $seatIdName")
                }
            }
        }


        if (peliculaObtenida != null) {
            for (button in allSeatRadioButtons) {
                val seatTag = button.tag.toString()
                if (peliculaObtenida.occupiedSeats.contains(seatTag)) {
                    button.isEnabled = false
                    button.background = ContextCompat.getDrawable(this, R.drawable.icon_seat_unavailable)
                }
            }
        }

        val confirm: Button = findViewById(R.id.confirmButton)
        confirm.setOnClickListener {
            var selectedRadioButton: RadioButton? = null
            var selectedId = -1

            for (button in allSeatRadioButtons) {
                if (button.isChecked) {
                    selectedRadioButton = button
                    selectedId = button.id
                    break
                }
            }

            Log.d("SeatSelectionDebug", "Selected ID on confirm click: $selectedId")
            Log.d("SeatSelectionDebug", "selectedRadioButton is null: ${selectedRadioButton == null}")
            Log.d("SeatSelectionDebug", "peliculaObtenida is null (in confirm click): ${peliculaObtenida == null}")


            if (selectedRadioButton != null && peliculaObtenida != null) {
                val seatTag = selectedRadioButton.tag.toString()
                Log.d("SeatSelectionDebug", "Attempting to book seat: $seatTag")


                if (!peliculaObtenida.occupiedSeats.contains(seatTag)) {
                    peliculaObtenida.occupiedSeats.add(seatTag)
                    selectedRadioButton.isEnabled = false

                    Toast.makeText(this, "Seat $seatTag has been booked! Enjoy the movie!", Toast.LENGTH_LONG).show()
                    Log.d("SeatSelectionDebug", "Seat $seatTag booked successfully. Occupied seats: ${peliculaObtenida.occupiedSeats}")


                } else {
                    Toast.makeText(this, "Ese asiento ya está ocupado", Toast.LENGTH_SHORT).show()
                    Log.d("SeatSelectionDebug", "Seat $seatTag is already occupied.")
                }
            } else {
                Toast.makeText(this, "Por favor, selecciona un asiento.", Toast.LENGTH_SHORT).show()
                Log.d("SeatSelectionDebug", "Condition failed: selectedRadioButton is null OR peliculaObtenida is null.")
            }
        }


        val radioGroups = listOf(row1, row2, row3, row4, row5)

        for (i in radioGroups.indices) {
            radioGroups[i].setOnCheckedChangeListener { group, checkedId ->
                Log.d("SeatSelectionDebug", "Checked changed for group ID ${group.id}, checkedId: $checkedId")

                if (checkedId != -1) {
                    for (j in radioGroups.indices) {
                        if (i != j) {
                            radioGroups[j].clearCheck()
                        }
                    }
                }
            }
        }
    }
}