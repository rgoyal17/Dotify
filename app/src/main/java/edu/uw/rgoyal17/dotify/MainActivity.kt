package edu.uw.rgoyal17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var randomNumber = Random.nextInt(0, 1000)
    private lateinit var btn: Button
    private lateinit var username: TextView
    private lateinit var editUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songCounter = findViewById<TextView>(R.id.textViewCounter)
        songCounter.text = "Song Counter: $randomNumber"

        val playImage = findViewById<ImageView>(R.id.imageViewPlay)
        playImage.setOnClickListener {
            randomNumber += 1
            songCounter.text = "Song Counter: $randomNumber"
        }

        val nextImage = findViewById<ImageView>(R.id.imageViewNext)
        nextImage.setOnClickListener {
            Toast.makeText(this, "Skipping to next track!", Toast.LENGTH_SHORT).show()
        }

        val prevImage = findViewById<ImageView>(R.id.imageViewPrevious)
        prevImage.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track!", Toast.LENGTH_SHORT).show()
        }

        username = findViewById(R.id.textViewUsername)
        editUsername = findViewById(R.id.editTextUsername)

        btn = findViewById(R.id.buttonChangeUser)
        btn.setOnClickListener() {
            btnClicked()
        }
    }

    fun btnClicked() {
        if (btn.text == "CHANGE USER") {
            username.visibility = View.GONE
            editUsername.visibility = View.VISIBLE
            btn.text = "APPLY"
        } else {
            editUsername.visibility = View.GONE
            username.visibility = View.VISIBLE
            username.text = editUsername.text
            btn.text = "CHANGE USER"
        }
    }
}