package edu.uw.rgoyal17.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.rgoyal17.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: (name: String, artist: String) -> Unit = {_, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]

        with(holder.binding) {
            ivAlbumIcon.setImageResource(song.smallImageID)
            tvSongTitle.text = song.title
            tvArtistName.text = song.artist

            root.setOnClickListener {
                onSongClickListener(song.title, song.artist)
            }
        }
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        this.listOfSongs = newListOfSongs
        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)

}