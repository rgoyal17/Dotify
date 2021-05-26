package edu.uw.rgoyal17.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.rgoyal17.dotify.NavGraphDirections
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.FragmentSettingsBinding
import edu.uw.rgoyal17.dotify.manager.SongNotificationManager
import edu.uw.rgoyal17.dotify.model.Song

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private lateinit var songNotificationManager: SongNotificationManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        songNotificationManager = SongNotificationManager(context)
    }

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

            songNotificationManager.isNotificationsEnabled = switchNotificationsEnabled.isChecked

            switchNotificationsEnabled.setOnCheckedChangeListener { _, isChecked ->
                songNotificationManager.isNotificationsEnabled = isChecked
            }
        }

        return binding.root
    }

}