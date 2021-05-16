package edu.uw.rgoyal17.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.uw.rgoyal17.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)
        val songData: Song = safeArgs.song

        with(binding) {
            btProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btStatistics.setOnClickListener {
                navController.navigate(NavGraphDirections.actionGlobalStatisticsFragment(songData))
            }

            btAbout.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }
        }

        return binding.root
    }

}