package edu.uw.rgoyal17.dotify

import android.app.Application
import edu.uw.rgoyal17.dotify.repository.DataRepository

class DotifyApplication: Application() {

    var counter: Int = 0
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
    }

}