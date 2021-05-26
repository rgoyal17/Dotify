package edu.uw.rgoyal17.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SongNotificationWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

    }

}