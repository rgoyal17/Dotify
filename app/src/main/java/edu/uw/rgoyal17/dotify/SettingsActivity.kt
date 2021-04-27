package edu.uw.rgoyal17.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.uw.rgoyal17.dotify.databinding.ActivitySettingsBinding

private const val SONG_KEY = "SONG_KEY"
private const val PLAY_COUNT_KEY = "PLAY_COUNT_KEY"

fun navigateToSettingsActivity(context: Context, song: Song, playCount: Int) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
        putInt(PLAY_COUNT_KEY, playCount)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

class SettingsActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHost) }
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {

        }
    }
}