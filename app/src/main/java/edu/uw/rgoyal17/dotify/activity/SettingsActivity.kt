package edu.uw.rgoyal17.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.ActivitySettingsBinding
import edu.uw.rgoyal17.dotify.model.Song

private const val SONG_KEY = "song"

fun navigateToSettingsActivity(context: Context, song: Song) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
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
        title = "Settings"
        navController.setGraph(R.navigation.nav_graph, intent.extras)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}