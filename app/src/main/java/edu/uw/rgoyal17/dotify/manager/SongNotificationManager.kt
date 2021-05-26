package edu.uw.rgoyal17.dotify.manager

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager
import edu.uw.rgoyal17.dotify.R

class SongNotificationManager(
        private val context: Context
) {

    private val workManager: WorkManager = WorkManager.getInstance(context)
    private val notificationManager = NotificationManagerCompat.from(context)
    var isNotificationsEnabled = true

    fun publishNewSongNotification() {

        val builder = NotificationCompat.Builder(context, NEW_EMAILS_CHANNEL_ID)    // channel id from creating the channel
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle("New Email from Post Malone")
                .setContentText("You a SUN FLOWWWWWAAAAAAAAA ${Random.nextInt()}")
                .setContentIntent(pendingIntent)    // sets the action when user clicks on notification
                .setAutoCancel(true)    // This will dismiss the notification tap
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    }
}