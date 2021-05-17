package edu.uw.rgoyal17.dotify.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import edu.uw.rgoyal17.dotify.DotifyApplication
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.ActivityMainBinding
import edu.uw.rgoyal17.dotify.model.Song
import kotlin.random.Random

private const val SONG_KEY = "song"
private const val PLAY_COUNT_KEY = "playCount"

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
    private lateinit var song: Song
    private lateinit var dotifyApp: DotifyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dotifyApp = this.applicationContext as DotifyApplication
        dotifyApp.counter = Random.nextInt(0, 1000)

        // recover play count if activity was destroyed and recreated
        if (savedInstanceState != null) {
            dotifyApp.counter = savedInstanceState.getInt(PLAY_COUNT_KEY, 0)
        }

        with(binding) {

            song = intent.getParcelableExtra<Song>(SONG_KEY) ?: return

            // update song name, title, and album image
            imageViewDotify.load(song.largeImageURL)
            textViewSongTitle.text = song.title
            textViewArtist.text = song.artist

            // set number of plays to playCount
            textViewCounter.text = root.context.getString(R.string.play_count, dotifyApp.counter)

            // increment the count of the number of plays
            imageViewPlay.setOnClickListener {
                dotifyApp.counter += 1
                textViewCounter.text = root.context.getString(R.string.play_count, dotifyApp.counter)
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAY_COUNT_KEY, dotifyApp.counter)
        super.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.player_settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.app_bar_search -> navigateToSettingsActivity(this@PlayerActivity, song)
        }
        return super.onOptionsItemSelected(item)
    }
}