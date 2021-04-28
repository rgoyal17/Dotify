package edu.uw.rgoyal17.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.rgoyal17.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        val songData: Song = safeArgs.song
        val playCount: Int = safeArgs.playCount

        with(binding) {
            ivSongImage.setImageResource(songData.largeImageID)
            tvPlayCount.text = root.context.getString(R.string.song_count, songData.title, playCount)
        }

        return binding.root
    }

}