package edu.uw.rgoyal17.dotify.manager

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.rgoyal17.dotify.DotifyApplication
import edu.uw.rgoyal17.dotify.model.SongList

class SongNotificationWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dotifyApplication: DotifyApplication by lazy { context.applicationContext as DotifyApplication }
    private val dataRepository by lazy { dotifyApplication.dataRepository }

    private val application by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { application.songNotificationManager }

    override suspend fun doWork(): Result {

        val songs: SongList = loadSongs() ?: return Result.failure()

        songNotificationManager.publishNewSongNotification(songs.songs.random())
        return Result.success()
    }

    private suspend fun loadSongs(): SongList? {
        var songList: SongList? = null
        runCatching {
            songList = dataRepository.getSongs()
        }.onFailure {
            Toast.makeText(context, "Error occurred when fetching songs", Toast.LENGTH_SHORT).show()
        }
        return songList
    }



}