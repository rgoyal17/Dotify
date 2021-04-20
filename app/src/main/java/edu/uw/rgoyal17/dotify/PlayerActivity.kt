package edu.uw.rgoyal17.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.Song
import edu.uw.rgoyal17.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

private const val SONG_KEY = "SONG_KEY"

fun navigateToPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java)

    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)

            if (song != null) {
                imageViewDotify.setImageResource(song.largeImageID)
                textViewSongTitle.text = song.title
                textViewArtist.text = song.artist
            }

            // set number of plays to a random number
            var randomNumber = Random.nextInt(0, 1000)
            textViewCounter.text = "$randomNumber plays"

            // increment the count of the number of plays
            imageViewPlay.setOnClickListener {
                randomNumber += 1
                textViewCounter.text = "$randomNumber plays"
            }

            // Toast a message when clicked on next button
            imageViewNext.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }

            // Toast a message when clicked on previous button
            imageViewPrevious.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }

            // change color of play count upon long press on cover image
            imageViewDotify.setOnLongClickListener {
                textViewCounter.setTextColor(Color.RED)
                true
            }
        }
    }
}