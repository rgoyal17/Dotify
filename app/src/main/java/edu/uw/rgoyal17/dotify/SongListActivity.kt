package edu.uw.rgoyal17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.rgoyal17.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"
        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            val songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            lateinit var playerSong: Song

            adapter.onSongClickListener = { song ->
                playerSong = song
                tvMiniPlayerText.text = root.context.getString(R.string.mini_player_text, song.title, song.artist)
                tvMiniPlayerText.visibility = View.VISIBLE
                miniPlayerButton.visibility = View.VISIBLE
            }

            tvMiniPlayerText.setOnClickListener {
                navigateToPlayerActivity(this@SongListActivity, playerSong)
            }

            miniPlayerButton.setOnClickListener {
                adapter.updateSongs(songs.toMutableList().shuffled())
            }
        }
    }
}