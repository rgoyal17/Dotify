package edu.uw.rgoyal17.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import edu.uw.rgoyal17.dotify.DotifyApplication
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.FragmentStatisticsBinding
import edu.uw.rgoyal17.dotify.model.Song

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()
    private lateinit var dotifyApp: DotifyApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        val songData: Song = safeArgs.song

        with(binding) {
            ivSongImage.load(songData.largeImageURL)
            tvPlayCount.text = root.context.getString(R.string.song_count, songData.title, dotifyApp.counter)
        }

        return binding.root
    }

}