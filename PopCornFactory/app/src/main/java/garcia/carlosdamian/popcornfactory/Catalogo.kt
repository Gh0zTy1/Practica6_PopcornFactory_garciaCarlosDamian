package garcia.carlosdamian.popcornfactory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Catalogo : AppCompatActivity() {

    private lateinit var app: MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        app = application as MyApp

        val seriesLocales = ArrayList<Pelicula>()
        val peliculasLocales = ArrayList<Pelicula>()

        cargarPeliculas(seriesLocales, peliculasLocales)

        val gridViewSeries: GridView = findViewById(R.id.gridViewSeries)
        val gridViewPeliculas: GridView = findViewById(R.id.gridViewPeliculas)

        gridViewSeries.adapter = PeliculaAdapter(seriesLocales, this)
        gridViewPeliculas.adapter = PeliculaAdapter(peliculasLocales, this)
    }

    fun cargarPeliculas(series: ArrayList<Pelicula>, peliculas: ArrayList<Pelicula>) {
        if (app.peliculas.isEmpty()) {

            val drHouse = Pelicula(
                "Dr. House", R.drawable.drhouse, R.drawable.drwhoheader, sinopsis =
                    "The series follows the life of anti-social, pain killer addict, witty and arrogant medical doctor Gregory " +
                            "House (Hugh Laurie) with only half a muscle in his right leg. He and his team of medical doctors try " +
                            "to cure complex and rare diseases from very ill ordinary people in the United States of America."
            )

            val smallville = Pelicula(
                "Smallville", R.drawable.smallville, R.drawable.smallvilleheader, sinopsis =
                    "The numerous miraculous rescues by the local wonder boy Clark have aroused suspicions amongst " +
                            "colonials of Smallville. Interestingly, the boy has managed to downplay his acts of various heroic " +
                            "egresses in the past. They say he's either too fast or has a penchant for finding trouble. He was " +
                            "found by Martha and Jonathan Kent on the day of the Meteor Shower, and subsequently adopted. " +
                            "Clark's friend Lex Luthor, the only heir of Luthorcorp, has been secretly investigating grounds for " +
                            "Clark's outlandish valor. However, on the face of it, Clark just seems a normal boy who's slightly " +
                            "more secretive than usual."
            )

            val drWho = Pelicula(
                "Dr. Who", R.drawable.drwho, R.drawable.drwhoheader, sinopsis =
                    "Traveling across time and space, the immortal time-lord known as 'The Doctor' travels across the " +
                            "universe with his many companions and his loyal shape-shifting space-ship: The TARDIS. The " +
                            "Doctor faces many threats across many generations: from The Daleks, The Cybermen and his time- " +
                            "lord adversary The Master to the sinister Davros, creator of The Daleks."
            )

            val bones = Pelicula(
                "Bones", R.drawable.bones, R.drawable.bonesheader, sinopsis =
                    "Dr. Temperance Brennan is a brilliant, but lonely, anthropologist whom is approached by an " +
                            "ambitious FBI agent, named Seely Booth, to help the bureau solve a series of unsolved crimes by " +
                            "identifying the long-dead bodies of missing persons by their bone structure. But both Agent Booth " +
                            "and Dr. Brennan and her team come up again a variety of interference from red tape, corruption, " +
                            "and local noncooperation."
            )

             val suits = Pelicula(
                "Suits", R.drawable.suits, R.drawable.suitsheader, sinopsis =
                    "While running from a drug deal gone bad, brilliant young college dropout Mike Ross slips into a job " +
                            "interview with one of New York City's best legal closers, Harvey Specter. Tired of cookie-cutter law- " +
                            "school grads, Harvey takes a gamble by hiring Mike on the spot after recognizing his raw talent and " +
                            "photographic memory. Mike and Harvey are a winning team. Although Mike is a genius, he still has " +
                            "a lot to learn about law; and while Harvey might seem like an emotionless, cold-blooded shark, " +
                            "Mike's sympathy and concern for their cases and clients will help remind Harvey why he went into " +
                            "law in the first place. Mike's other allies in the office include the firm's best paralegal Rachel and " +
                            "Harvey's no-nonsense assistant Donna. Proving to be an irrepressible duo and invaluable to the practice, " +
                            "Mike and Harvey must keep their secret from everyone including managing partner Jessica and Harvey's " +
                            "archnemesis Louis, who seems intent on making Mike's life as difficult as possible."
            )


                val friends = Pelicula(
                "Friends", R.drawable.friends, R.drawable.friendsheader, sinopsis =
                    "Rachel Green, Ross Geller, Monica Geller, Joey Tribbiani, Chandler Bing and Phoebe Buffay are " +
                            "six 20 something year-olds, living off of one another in the heart of New York City. Over the course " +
                            "of ten years, this average group of buddies goes through massive mayhem, family trouble, past and " +
                            "future romances, fights, laughs, tears and surprises as they learn what it really means to be a " +
                            "friend."
            )


            val bigHero = Pelicula(
                "Big Hero", R.drawable.bighero6, R.drawable.headerbighero6, sinopsis =
                    "When a devastating event befalls the city of San Fransokyo and catapults Hiro into the " +
                            "midst of danger, he turns to Baymax and his close friends adrenaline junkie Go Go " +
                            "Tomago, neatnik Wasabi, chemistry whiz Honey Lemon and fanboy Fred. Determined to " +
                            "uncover the mystery, Hiro transforms his friends into a band of high-tech heroes called " +
                            "\"Big Hero 6.\""
            )

            val milNovecientosDiecisiete = Pelicula(
                "1917", R.drawable.milnovecientos, R.drawable.milnovecientosheader, sinopsis =
                    "British trenches somewhere in France. World war has been going on for the third year, " +
                            "heroic illusions have dissipated; general mood - boredom and fatigue. Stuff the belly, " +
                            "sleep, return home to Christmas Eve. On another quiet day, when nothing happens, two " +
                            "young soldiers, Blake and Schofield, are summoned to the general, who instructs them to " +
                            "send an important message to Colonel MacKenzie in the Second Devonshire Battalion, " +
                            "whose telephone connection was cut off by the enemy."
            )

            val leapYear = Pelicula(
                "Leap Year", R.drawable.leapyear, R.drawable.leapyearheader, sinopsis =
                    "A woman who has an elaborate scheme to propose to her boyfriend on Leap Day, an Irish " +
                            "tradition which occurs every time the date February 29 rolls around, faces a major setback " +
                            "when bad weather threatens to derail her planned trip to Dublin. With the help of an " +
                            "innkeeper, however, her cross-country odyssey just might result in her getting engaged."
            )

            val menInBlack = Pelicula(
                "Men in Black", R.drawable.mib, R.drawable.mibheader, sinopsis =
                    "Based off of the comic book. Unbeknownst to other people, there is a private agency code " +
                            "named MiB. This agency is some kind of extra terrestrial surveillance corporation. Then, " +
                            "one of the agency's finest men only going by the name \"K\" (Tommy Lee Jones), is " +
                            "recruiting for a new addition to the agency. He has chosen James Edwards (Will Smith) of " +
                            "the N.Y.P.D. Then, one day, a flying saucer crashes into Earth. This was an alien a part of " +
                            "the \"Bug\" race. He takes the body of a farmer (Vincent D'Onofrio) and heads to New York. " +
                            "He is searching for a super energy source called \"The Galaxy\". Now, Agents J and K must " +
                            "stop the bug before it can escape with the galaxy."
            )
            val toyStory = Pelicula("Toy Story", R.drawable.toystory, R.drawable.toystoryheader, sinopsis = "Toy Story is about the 'secret life of toys' when people are not around. When Buzz Lightyear, a space-ranger, takes Woody's place as Andy's favorite toy, Woody doesn't like the situation and gets into a fight with Buzz. Accidentaly Buzz falls out the window and Woody is accused by all the other toys of having killed him. He has to go out of the house to look for him so that they can both return to Andys room. But while on the outside they get into all kind of trouble while trying to get home.")
            val inception = Pelicula("Inception", R.drawable.inception, R.drawable.inceptionheader, sinopsis = "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible, inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea, but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.\n")

            val todos = listOf(drHouse, smallville, drWho, bones, suits, friends,
                bigHero, milNovecientosDiecisiete, leapYear, menInBlack, toyStory, inception)

            app.peliculas.addAll(todos)

            for (pelicula in todos) {
                if (pelicula.titulo in listOf("Dr. House", "Smallville", "Dr. Who", "Bones", "Suits", "Friends")) {
                    series.add(pelicula)
                } else {
                    peliculas.add(pelicula)
                }
            }
        }
    }

    class PeliculaAdapter(private val peliculas: ArrayList<Pelicula>, private val context: Context?) : BaseAdapter() {

        override fun getCount(): Int = peliculas.size

        override fun getItem(position: Int): Any = peliculas[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val pelicula = peliculas[position]
            val vista = LayoutInflater.from(context).inflate(R.layout.pelicula, parent, false)

            val imagen = vista.findViewById<ImageView>(R.id.iv_pelicula)
            val titulo = vista.findViewById<TextView>(R.id.tv_titulo)

            imagen.setImageResource(pelicula.image)
            titulo.text = pelicula.titulo

            imagen.setOnClickListener {
                context?.let {
                    val intento = Intent(it, DetallePelicula::class.java).apply {
                        putExtra("titulo", pelicula.titulo)
                        putExtra("imagen", pelicula.image)
                        putExtra("header", pelicula.header)
                        putExtra("sinopsis", pelicula.sinopsis)
                    }
                    it.startActivity(intento)
                }
            }

            return vista
        }
    }
}
