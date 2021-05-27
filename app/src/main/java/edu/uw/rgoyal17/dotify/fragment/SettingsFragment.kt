package edu.uw.rgoyal17.dotify.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.rgoyal17.dotify.DotifyApplication
import edu.uw.rgoyal17.dotify.NavGraphDirections
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.databinding.FragmentSettingsBinding
import edu.uw.rgoyal17.dotify.manager.SongNotificationManager
import edu.uw.rgoyal17.dotify.model.Song

const val NOTIFICATIONS_ENABLED_PREF_KEY = "notifications_enabled"

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private lateinit var dotifyApplication: DotifyApplication
    private lateinit var songNotificationManager: SongNotificationManager
    private lateinit var preferences: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApplication = context.applicationContext as DotifyApplication
        songNotificationManager = dotifyApplication.songNotificationManager
        preferences = dotifyApplication.preferences
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

            switchNotificationsEnabled.isChecked = preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false)

            switchNotificationsEnabled.setOnCheckedChangeListener { _, isChecked ->
                preferences.edit {
                    putBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, isChecked)
                }

                if (isChecked) {
                    songNotificationManager.publishNotificationsPeriodically()
                } else {
                    songNotificationManager.stopPublishingNotificationsPeriodically()
                }
            }
        }

        return binding.root
    }

}