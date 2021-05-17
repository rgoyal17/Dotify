package edu.uw.rgoyal17.dotify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.uw.rgoyal17.dotify.callback.SongsDiffCallback
import edu.uw.rgoyal17.dotify.databinding.ItemSongBinding
import edu.uw.rgoyal17.dotify.model.Song

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: (song: Song) -> Unit = {_ -> }

    var onSongLongClickListener: (song: Song) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]

        with(holder.binding) {
            ivAlbumIcon.load(song.smallImageURL)
            tvSongTitle.text = song.title
            tvArtistName.text = song.artist

            root.setOnClickListener {
                onSongClickListener(song)
            }

            root.setOnLongClickListener {
                onSongLongClickListener(song)
                true
            }
        }
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        val callback = SongsDiffCallback(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        this.listOfSongs = newListOfSongs
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)

}