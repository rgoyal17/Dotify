package edu.uw.rgoyal17.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import edu.uw.rgoyal17.dotify.R
import edu.uw.rgoyal17.dotify.activity.PlayerActivity
import edu.uw.rgoyal17.dotify.model.Song
import edu.uw.rgoyal17.dotify.model.SongList
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private const val NOTIFICATION_TAG = "NOTIFICATION_TAG"
private const val NEW_SONGS_CHANNEL_ID = "NEW_SONGS_CHANNEL_ID"
private const val SONG_KEY = "song"

class SongNotificationManager(
        private val context: Context
) {

    private val workManager: WorkManager = WorkManager.getInstance(context)
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        // Initialize all channels
        initNotificationChannels()
    }


    fun publishNewSongNotification(song: Song) {

        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_KEY, song)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, NEW_SONGS_CHANNEL_ID)    // channel id from creating the channel
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle("${song.artist} just released a new song!!!")
                .setContentText("Listen to ${song.title} now on Dotify")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)    // This will dismiss the notification tap
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Tell the OS to publish the notification using the info
        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    fun publishNotificationsPeriodically() {

        if (isPublishNotificationRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongNotificationWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()
                )
                .addTag(NOTIFICATION_TAG)
                .build()
        workManager.enqueue(request)
    }

    fun stopPublishingNotificationsPeriodically() {
        workManager.cancelAllWorkByTag(NOTIFICATION_TAG)
    }

    private fun isPublishNotificationRunning(): Boolean {
        return workManager.getWorkInfosByTag(NOTIFICATION_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

    private fun initNotificationChannels() {
        initNewSongsChannel()
    }

    private fun initNewSongsChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_music)
            val descriptionText = context.getString(R.string.new_music_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_SONGS_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }
}