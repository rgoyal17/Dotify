package edu.uw.rgoyal17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var randomNumber = Random.nextInt(0, 1000)
    private lateinit var songCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songCounter = findViewById(R.id.textViewCounter)
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
    }
}