package edu.uw.rgoyal17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.rgoyal17.dotify.databinding.ActivitySongListBinding

private const val SONG_KEY = "song"

class SongListActivity : AppCompatActivity() {

    private var playerSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        title = "All Songs"

        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            // get all songs and setup the adapter
            var songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            // show the mini player if it was shown before activity got destroyed and is recreated now
            // (if it was not shown before, then song = null so we would not show the mini player)
            if (savedInstanceState != null) {
                val song = savedInstanceState.getParcelable<Song>(SONG_KEY)
                if (song != null) {
                    playerSong = song
                    tvMiniPlayerText.text = root.context.getString(R.string.mini_player_text, song.title, song.artist)
                    tvMiniPlayerText.visibility = View.VISIBLE
                    miniPlayerButton.visibility = View.VISIBLE
                }
            }

            // setup the mini player when clicked on a song
            adapter.onSongClickListener = { song ->
                playerSong = song
                tvMiniPlayerText.text = root.context.getString(R.string.mini_player_text, song.title, song.artist)
                if (tvMiniPlayerText.visibility == View.GONE || miniPlayerButton.visibility == View.GONE) {
                    tvMiniPlayerText.visibility = View.VISIBLE
                    miniPlayerButton.visibility = View.VISIBLE
                }
            }

            // Long pressing on a song deletes that song on the list.
            adapter.onSongLongClickListener = { song ->
                songs = songs.toMutableList().apply { remove(song) }
                adapter.updateSongs(songs)
                Toast.makeText(this@SongListActivity, "${song.title} was removed", Toast.LENGTH_SHORT).show()
            }

            // navigate to larger music player when clicked on mini player
            tvMiniPlayerText.setOnClickListener {
                val song = playerSong ?: return@setOnClickListener
                navigateToPlayerActivity(this@SongListActivity, song)
            }

            // shuffle songs
            miniPlayerButton.setOnClickListener {
                adapter.updateSongs(songs.toMutableList().shuffled())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(SONG_KEY, playerSong)
        super.onSaveInstanceState(outState)
    }
}