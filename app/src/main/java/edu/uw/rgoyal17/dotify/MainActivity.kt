package edu.uw.rgoyal17.dotify

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.uw.rgoyal17.dotify.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        // set number of plays to a random number
        var randomNumber = Random.nextInt(0, 1000)
        binding.textViewCounter.text = "$randomNumber plays"

        // increment the count of the number of plays
        binding.imageViewPlay.setOnClickListener {
            randomNumber += 1
            binding.textViewCounter.text = "$randomNumber plays"
        }

        // Toast a message when clicked on next button
        binding.imageViewNext.setOnClickListener {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        // Toast a message when clicked on previous button
        binding.imageViewPrevious.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        // call btnClicked() when button is clicked
        binding.buttonChangeUser.setOnClickListener {
            btnClicked()
        }

        // change color of play count upon long press on cover image
        binding.imageViewDotify.setOnLongClickListener {
            binding.textViewCounter.setTextColor(Color.RED)
            true
        }
    }


    fun btnClicked() {
        if (binding.buttonChangeUser.text == "CHANGE USER") {
            binding.textViewUsername.visibility = View.GONE
            binding.editTextUsername.visibility = View.VISIBLE
            binding.buttonChangeUser.text = "APPLY"
        } else {
            if (binding.editTextUsername.text.toString() == "") {
                Toast.makeText(this, "Please enter a username!", Toast.LENGTH_SHORT).show()
            } else {
                binding.editTextUsername.visibility = View.GONE
                binding.textViewUsername.visibility = View.VISIBLE
                binding.textViewUsername.text = binding.editTextUsername.text
                binding.buttonChangeUser.text = "CHANGE USER"
            }
        }
    }
}