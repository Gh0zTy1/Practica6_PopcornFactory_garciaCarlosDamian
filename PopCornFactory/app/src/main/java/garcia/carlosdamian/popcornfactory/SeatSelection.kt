package garcia.carlosdamian.popcornfactory

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity // Ensure this import is present

class SeatSelection : AppCompatActivity() {

    private lateinit var app: MyApp
    private lateinit var row1: RadioGroup
    private lateinit var row2: RadioGroup
    private lateinit var row3: RadioGroup
    private lateinit var row4: RadioGroup
    private lateinit var row5: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        app = application as MyApp

        val titulo = intent.getStringExtra("titulo") ?: ""

        val peliculaObtenida = app.peliculas.find { it.titulo == titulo }


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


        if (peliculaObtenida != null) {

            val allRadioButtons = mutableListOf<RadioButton>()
            val rows = listOf(row1, row2, row3, row4, row5)

            val rowPrefixes = listOf("A", "B", "C", "D", "E")

            for ((rowIndex, radioGroup) in rows.withIndex()) {
                for (seatIndex in 1..6) {
                    val seatIdName = "seat_${rowIndex + 1}_$seatIndex"
                    val resId = resources.getIdentifier(seatIdName, "id", packageName)
                    if (resId != 0) {
                        val radioButton = findViewById<RadioButton>(resId)
                        allRadioButtons.add(radioButton)
                    } else {
                        Log.e("SeatSelection", "Resource ID not found for $seatIdName")
                    }
                }
            }

            for (button in allRadioButtons) {

                val seatTag = button.tag.toString()
                if (peliculaObtenida.occupiedSeats.contains(seatTag)) {
                    button.isEnabled = false

                    button.background = resources.getDrawable(R.drawable.icon_seat_unavailable)
                }
            }
        }

        val confirm: Button = findViewById(R.id.confirmButton)
        confirm.setOnClickListener {
            var selectedId = -1
            var selectedRow: RadioGroup? = null


            when {
                row1.checkedRadioButtonId != -1 -> { selectedId = row1.checkedRadioButtonId; selectedRow = row1 }
                row2.checkedRadioButtonId != -1 -> { selectedId = row2.checkedRadioButtonId; selectedRow = row2 }
                row3.checkedRadioButtonId != -1 -> { selectedId = row3.checkedRadioButtonId; selectedRow = row3 }
                row4.checkedRadioButtonId != -1 -> { selectedId = row4.checkedRadioButtonId; selectedRow = row4 }
                row5.checkedRadioButtonId != -1 -> { selectedId = row5.checkedRadioButtonId; selectedRow = row5 } // Check 5th row
            }

            if (selectedId != -1 && peliculaObtenida != null) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val seatTag = selectedRadioButton.tag.toString()

                if (!peliculaObtenida.occupiedSeats.contains(seatTag)) {
                    peliculaObtenida.occupiedSeats.add(seatTag)
                    selectedRadioButton.isEnabled = false

                    Toast.makeText(this, "Seat $seatTag has been booked! Enjoy the movie!", Toast.LENGTH_LONG).show()


                } else {
                    Toast.makeText(this, "Ese asiento ya está ocupado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, selecciona un asiento.", Toast.LENGTH_SHORT).show()
            }
        }


        val radioGroups = listOf(row1, row2, row3, row4, row5)

        for (i in radioGroups.indices) {
            radioGroups[i].setOnCheckedChangeListener { _, checkedId ->
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