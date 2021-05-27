package edu.uw.rgoyal17.dotify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import edu.uw.rgoyal17.dotify.manager.SongNotificationManager
import edu.uw.rgoyal17.dotify.repository.DataRepository

const val NOTIFICATIONS_ENABLED_PREF_KEY = "notifications_enabled"

class DotifyApplication: Application() {

    var counter: Int = 0
    lateinit var dataRepository: DataRepository
    lateinit var songNotificationManager: SongNotificationManager
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        songNotificationManager = SongNotificationManager(this)
        preferences = getSharedPreferences(NOTIFICATIONS_ENABLED_PREF_KEY, Context.MODE_PRIVATE)
    }

}