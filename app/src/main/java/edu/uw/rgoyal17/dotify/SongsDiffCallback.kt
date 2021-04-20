package edu.uw.rgoyal17.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongsDiffCallback(private val newSongs: List<Song>, private val oldSongs: List<Song>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newSongs[newItemPosition].id == oldSongs[oldItemPosition].id
    }

    override fun getOldListSize(): Int = oldSongs.size

    override fun getNewListSize(): Int = newSongs.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSongs[newItemPosition]
        val oldSong = oldSongs[oldItemPosition]
        return newSong.title == oldSong.title && newSong.artist == oldSong.artist
    }
}